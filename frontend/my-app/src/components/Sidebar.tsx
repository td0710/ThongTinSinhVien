import React from "react";
import { Flex, Grid, Menu } from "antd";
import {
  UploadOutlined,
  UserOutlined,
  VideoCameraOutlined,
} from "@ant-design/icons";
import { useNavigate } from "react-router-dom";

interface SidebarProps {
  collapsed: boolean;
}
const { useBreakpoint } = Grid;

const Sidebar: React.FC<SidebarProps> = ({ collapsed }) => {
  const navigate = useNavigate();
  const handleClick = (e: any) => {
    switch (e.key) {
      case "1":
        navigate("/thongtincanhan");
        break;
      case "2":
        navigate("/dangky");
        break;
      case "3":
        navigate("/upload");
        break;
      default:
        break;
    }
  };
  return (
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
            label: "nav 1",
          },
          {
            key: "2",
            icon: <VideoCameraOutlined />,
            label: "nav 2",
          },
          {
            key: "3",
            icon: <UploadOutlined />,
            label: "nav 3",
          },
        ]}
      />
    </div>
  );
};

export default Sidebar;
