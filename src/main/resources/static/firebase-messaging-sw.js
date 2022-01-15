
importScripts('https://www.gstatic.com/firebasejs/8.10.0/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.0/firebase-messaging.js');
importScripts('https://www.gstatic.com/firebasejs/8.10.0/firebase-analytics.js');



firebase.initializeApp({
  apiKey: "AIzaSyCGNHhyLLqblrMK3R6uEKUHxQWl15SQv8k",
  authDomain: "fir-demo-30d0e.firebaseapp.com",
  projectId: "fir-demo-30d0e",
  storageBucket: "fir-demo-30d0e.appspot.com",
  messagingSenderId: "681205052992",
  appId: "1:681205052992:web:f97de6fa70ef2f0c20d1a5",
  measurementId: "G-NQT1FDMEFE",
});



self.addEventListener('notificationclick', function (event) {
  event.notification.close();
  event.waitUntil(
    clients.openWindow(linkEven));
});
// Retrieve an instance of Firebase Messaging so that it can handle background
// messages.
let linkEven;
const messaging = firebase.messaging();
// xu ly thong bao khi ung dung ngung hoat dong
messaging.setBackgroundMessageHandler((payload)=>{
   linkEven = payload.data.link;
   const notificationTitle = payload.data.title;
   const notificationOptions = {
    body: payload.data.content,
    icon: "https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Logo_PTIT_University.png/900px-Logo_PTIT_University.png",
  };
  return self.registration.showNotification(
    notificationTitle,
    notificationOptions,
    );
})


