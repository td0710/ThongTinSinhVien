import React from "react";
import { Layout, Menu } from "antd";
import {
  CarOutlined,
  ContainerOutlined,
  LogoutOutlined,
  UserOutlined,
} from "@ant-design/icons";
import { useNavigate } from "react-router-dom";

interface SidebarProps {
  collapsed: boolean;
}

const { Sider } = Layout;

const Sidebar: React.FC<SidebarProps> = ({ collapsed }) => {
  const navigate = useNavigate();

  const handleClick = (e: any) => {
    switch (e.key) {
      case "1":
        navigate("/thongtincanhan");
        break;
      case "2":
        navigate("/giayxacnhansinhvien");
        break;
      case "3":
        navigate("/dangkyvexebuyt");
        break;
      case "logout":
        localStorage.removeItem("token");
        navigate("/login");
        break;
      default:
        break;
    }
  };

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        height: "100%",
      }}
    >
      <div>
        <div style={{ display: "flex", justifyContent: "center" }}>
          <img
            style={{
              height: 50,
              margin: 16,
              borderRadius: 6,
              width: 80,
            }}
            src="https://qldt.utc.edu.vn/congthongtin/logo.png"
            alt=""
          />
        </div>
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={["1"]}
          onClick={handleClick}
          items={[
            {
              key: "1",
              icon: <UserOutlined />,
              label: "Thông tin cá nhân",
            },
            {
              key: "2",
              icon: <ContainerOutlined />,
              label: "Xin cấp giấy Xác nhận sinh viên",
            },
            {
              key: "3",
              icon: <CarOutlined />,
              label: "Đăng ký vé tháng xe buýt",
            },
          ]}
        />
      </div>

      <div style={{ flexGrow: 1 }} />

      <Menu
        theme="dark"
        mode="inline"
        onClick={handleClick}
        style={{ fontSize: 16 }}
        items={[
          {
            key: "logout",
            icon: <LogoutOutlined />,
            label: "Đăng xuất",
          },
        ]}
      />
    </div>
  );
};

export default Sidebar;
