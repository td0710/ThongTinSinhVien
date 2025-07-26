import MainLayout from "../pages/Layout";
import { LoadingPage } from "../pages/LoadingPage";
import LoginPage from "../pages/LoginPage";
import { QuanLyThongTinSinhVienPage } from "../pages/QuanLyThongTinSinhVienPage";
import { ProtectedRoute } from "./ProtectedRoute";
import { PublicRoute } from "./PublicRoute";

const withLayout = (component: React.ReactNode) => (
  <MainLayout>{component}</MainLayout>
);

const protect = (component: React.ReactNode) => (
  <ProtectedRoute>{component}</ProtectedRoute>
);

const AppRoutes = [
  {
    path: "/auth-callback",
    element: <LoadingPage></LoadingPage>,
  },
  {
    path: "/login",
    element: (
      <PublicRoute>
        <LoginPage />
      </PublicRoute>
    ),
  },
  {
    index: true,
    element: protect(withLayout(<QuanLyThongTinSinhVienPage />)),
  },
  {
    path: "/",
    element: protect(withLayout(<QuanLyThongTinSinhVienPage />)),
  },
  {
    path: "/quanlythongtin",
    element: protect(withLayout(<QuanLyThongTinSinhVienPage />)),
  },
];

export default AppRoutes;
