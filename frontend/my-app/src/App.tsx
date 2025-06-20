import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AppRoutes from "./AppRoutes";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {AppRoutes.map((route, index) => {
          const { element, ...rest } = route;
          return <Route key={index} {...rest} element={element} />;
        })}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
