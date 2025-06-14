import React from "react";
import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import MainLayout from "./pages/Layout";
import AppRoutes from "./AppRoutes";

function App() {
  return (
    <BrowserRouter>
      <MainLayout>
        <Routes>
          {AppRoutes.map((route, index) => {
            const { element, ...rest } = route;
            return <Route key={index} {...rest} element={element} />;
          })}
        </Routes>
      </MainLayout>
    </BrowserRouter>
  );
}

export default App;
