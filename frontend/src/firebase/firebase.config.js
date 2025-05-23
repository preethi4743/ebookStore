// Import the functions you need from the SDKs you need

import { getAuth } from "firebase/auth";
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyDcsMeePzC0qTp31ZL_pihrRfL6fB8oXI0",
  authDomain: "ebookstore-3f34a.firebaseapp.com",
  projectId: "ebookstore-3f34a",
  storageBucket: "ebookstore-3f34a.firebasestorage.app",
  messagingSenderId: "822474174923",
  appId: "1:822474174923:web:6f2bd76e1a8fff87e9464e",
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
