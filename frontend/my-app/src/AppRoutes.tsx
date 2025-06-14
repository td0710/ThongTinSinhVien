import { DangKyXeBuytPage } from "./pages/DangKyXeBuytPage";
import { ThongTinCaNhanPage } from "./pages/ThongTinCaNhanPage";

const AppRoutes = [
  {
    index: true,
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
];

export default AppRoutes;
