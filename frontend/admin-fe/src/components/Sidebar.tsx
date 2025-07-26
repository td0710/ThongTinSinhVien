import React from "react";
import { Menu } from "antd";
import { LogoutOutlined, UserOutlined } from "@ant-design/icons";
import { useNavigate, useLocation } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

interface SidebarProps {
  collapsed: boolean;
  setLoading: (value: boolean) => void;
}

const Sidebar: React.FC<SidebarProps> = React.memo(
  ({ collapsed, setLoading }) => {
    const navigate = useNavigate();
    const location = useLocation();
    const { signOut } = useAuth();

    const handleClick = (e: any) => {
      switch (e.key) {
        case "1":
          navigate("/quanlythongtin");
          break;

        case "logout":
          setLoading(true);
          setTimeout(() => {
            signOut();
            setLoading(false);
          }, 300);
          break;
        default:
          break;
      }
    };

    const getSelectedKey = () => {
      if (location.pathname.startsWith("/quanlythongtin")) return "1";

      return "";
    };

    return (
      <div style={{ display: "flex", flexDirection: "column", height: "100%" }}>
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
              alt="Logo"
            />
          </div>

          <Menu
            theme="dark"
            mode="inline"
            selectedKeys={[getSelectedKey()]}
            onClick={handleClick}
            items={[
              {
                key: "1",
                icon: <UserOutlined />,
                label: "Quản lý thông tin sinh viên",
              },
            ]}
          />
        </div>

        <div style={{ flexGrow: 1 }} />

        <Menu
          theme="dark"
          mode="inline"
          onClick={handleClick}
          selectedKeys={[]}
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
  }
);

export default Sidebar;
