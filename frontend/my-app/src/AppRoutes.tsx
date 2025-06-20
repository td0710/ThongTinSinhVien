import { DangKyXeBuytPage } from "./pages/DangKyXeBuytPage";
import { GiayXacNhanSinhVienPage } from "./pages/GiayXacNhanSinhVienPage";
import { Loading } from "./pages/Loading";
import { ThongTinCaNhanPage } from "./pages/ThongTinCaNhanPage";
import MainLayout from "./pages/Layout";

const withLayout = (component: React.ReactNode) => (
  <MainLayout>{component}</MainLayout>
);

const AppRoutes = [
  {
    path: "/loading",
    element: <Loading />,
  },
  {
    index: true,
    element: withLayout(<ThongTinCaNhanPage />),
  },
  {
    path: "/",
    element: withLayout(<ThongTinCaNhanPage />),
  },
  {
    path: "/thongtincanhan",
    element: withLayout(<ThongTinCaNhanPage />),
  },
  {
    path: "/dangkyvexebuyt",
    element: withLayout(<DangKyXeBuytPage />),
  },
  {
    path: "/dangkyxebuyt",
    element: withLayout(<DangKyXeBuytPage />),
  },
  {
    path: "/giayxacnhansinhvien",
    element: withLayout(<GiayXacNhanSinhVienPage />),
  },
];

export default AppRoutes;
