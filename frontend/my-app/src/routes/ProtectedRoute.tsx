import { useEffect } from "react";
import { useAuth } from "../hooks/useAuth";

export const ProtectedRoute = ({ children }: { children: React.ReactNode }) => {
  const { isAuthenticated, isCheckingAuth } = useAuth();

  useEffect(() => {
    if (!isCheckingAuth && !isAuthenticated) {
      window.location.href = `https://login.microsoftonline.com/${process.env.REACT_APP_AZURE_TENANT_ID}/oauth2/v2.0/authorize?client_id=${process.env.REACT_APP_AZURE_CLIENT_ID}&response_type=code&redirect_uri=${process.env.REACT_APP_AZURE_REDIRECT_URI}&response_mode=query&scope=openid email profile&state=abc123`;
    }
  }, [isAuthenticated, isCheckingAuth]);

  return isAuthenticated ? <>{children}</> : null;
};
