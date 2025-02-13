// 세션 ID 가져오기 함수
async function getSessionId() {
    try {
        const response = await fetch("http://localhost:8080/api/session-id", {
            credentials: "include" // 세션 쿠키 포함 (필수)
        });
        if (!response.ok) throw new Error("서버 응답 오류");

        const sessionId = await response.text(); // 서버에서 받은 세션 ID
        console.log("🎟️ 서버에서 받은 세션 ID:", sessionId);

        document.getElementById("session-id").innerText = sessionId;
        return sessionId;
    } catch (error) {
        console.error("❌ 세션 ID 가져오기 실패:", error);
        return null;
    }
}

document.getElementById("session-id").innerText = "empty";

// 웹소켓 연결
const socket = new SockJS("http://localhost:8080/ws-stomp"); // 서버 주소
const stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log("✅ 연결 성공: " + frame);

    // 서버로부터 세션 ID 불러오기
    const sessionId = await getSessionId(); // 세션 ID 가져오기
    
    document.getElementById("session-id").innerText = await getSessionId(); // 세션 ID 가져오기

    // 구독 이벤트 설정
    stompClient.subscribe("/sub/channel/100", function (message) {
        console.log("📩 받은 메시지: ", message.body);
        const msgList = document.getElementById("messages");
        const newMsg = document.createElement("li");
        newMsg.textContent = message.body;
        msgList.appendChild(newMsg);
    });
}, function (error) {
    console.error("❌ 연결 실패: ", error);
});

// 메시지 보내기 함수
function sendMessage() {
    const messageContent = document.getElementById("message-box").value;
    if (messageContent.trim() !== "") {
        stompClient.send("/pub/channel", {}, JSON.stringify({
            userId: sessionId, 
            groupId: "100", 
            content: messageContent
        }));
        document.getElementById("message-box").value = "";
    }
}

// 페이지 닫을 때 연결 해제
window.addEventListener("beforeunload", function () {
    stompClient.disconnect(function () {
        console.log("🔌 연결 종료");
    });
});