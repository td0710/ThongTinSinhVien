import { DangKyXeBuytPage } from "../pages/DangKyXeBuytPage";
import { GiayXacNhanSinhVienPage } from "../pages/GiayXacNhanSinhVienPage";
import { Loading } from "../pages/Loading";
import { ThongTinCaNhanPage } from "../pages/ThongTinCaNhanPage";
import MainLayout from "../pages/Layout";
import { ProtectedRoute } from "./ProtectedRoute";
import LoginPage from "../pages/LoginPage";

const withLayout = (component: React.ReactNode) => (
  <MainLayout>{component}</MainLayout>
);

const AppRoutes = [
  {
    path: "/auth-callback",
    element: <Loading />,
  },

  {
    index: true,
    element: (
      <ProtectedRoute>{withLayout(<ThongTinCaNhanPage />)}</ProtectedRoute>
    ),
  },
  {
    path: "/",
    element: (
      <ProtectedRoute>{withLayout(<ThongTinCaNhanPage />)}</ProtectedRoute>
    ),
  },
  {
    path: "/thongtincanhan",
    element: <ThongTinCaNhanPage />,
  },
  {
    path: "/dangkyvexebuyt",
    element: (
      <ProtectedRoute>{withLayout(<DangKyXeBuytPage />)}</ProtectedRoute>
    ),
  },
  {
    path: "/dangkyxebuyt",
    element: (
      <ProtectedRoute>{withLayout(<DangKyXeBuytPage />)}</ProtectedRoute>
    ),
  },
  {
    path: "/giayxacnhansinhvien",
    element: (
      <ProtectedRoute>{withLayout(<GiayXacNhanSinhVienPage />)}</ProtectedRoute>
    ),
  },
];

export default AppRoutes;
