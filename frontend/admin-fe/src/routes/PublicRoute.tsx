import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

export const PublicRoute = ({ children }: { children: React.ReactNode }) => {
  const { isAuthenticated, isCheckingAuth } = useAuth();

  const navigate = useNavigate();
  useEffect(() => {
    if (isAuthenticated) {
      navigate("/");
    }
  }, [isAuthenticated, isCheckingAuth]);
  return <>{children}</>;
};
