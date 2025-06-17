import { DangKyXeBuytPage } from "./pages/DangKyXeBuytPage";
import { GiayXacNhanSinhVienPage } from "./pages/GiayXacNhanSinhVienPage";
import { ThongTinCaNhanPage } from "./pages/ThongTinCaNhanPage";

const AppRoutes = [
  {
    index: true,
    element: <ThongTinCaNhanPage></ThongTinCaNhanPage>,
  },
  {
    path: "/",
    element: <ThongTinCaNhanPage></ThongTinCaNhanPage>,
  },
  {
    path: "/thongtincanhan",
    element: <ThongTinCaNhanPage />,
  },
  {
    path: "/dangkyvexebuyt",
    element: <DangKyXeBuytPage />,
  },
  {
    path: "/giayxacnhansinhvien",
    element: <GiayXacNhanSinhVienPage />,
  },
];

export default AppRoutes;
