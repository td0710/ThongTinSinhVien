import { useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";
import { useEffect } from "react";

export const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
  const { isAuthenticated, isCheckingAuth } = useAuth();
  const navigate = useNavigate();
  useEffect(() => {
    if (!isCheckingAuth && !isAuthenticated) {
      navigate("/login");
    }
  }, [isAuthenticated, isCheckingAuth]);

  return isAuthenticated ? <>{children}</> : null;
};
