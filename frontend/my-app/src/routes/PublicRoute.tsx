import { useEffect } from "react";
import { useAuth } from "../hooks/useAuth";
import { Navigate, useNavigate } from "react-router-dom";

export const PublicRoute = ({ children }: { children: React.ReactNode }) => {
  const { isAuthenticated, isCheckingAuth } = useAuth();

  const navigate = useNavigate();
  useEffect(() => {
    if (isAuthenticated) {
      navigate("/");
    } else {
    }
  }, [isAuthenticated, isCheckingAuth]);
  return <>{children}</>;
};
