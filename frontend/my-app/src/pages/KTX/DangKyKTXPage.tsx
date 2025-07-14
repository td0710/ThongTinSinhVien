import { AppstoreOutlined, ProfileOutlined } from "@ant-design/icons";
import { Card, Menu, MenuProps, Typography } from "antd";
import { useState } from "react";
import { TatCaPhongPage } from "./TatCaPhongPage";
import { TheoDoiVaPhongCuaToiPage } from "./TheoDoiVaPhongCuaToiPage";
const { Paragraph, Title, Text } = Typography;

type MenuItem = Required<MenuProps>["items"][number];

const items: MenuItem[] = [
  {
    label: "Tất cả các phòng",
    key: "phong",
    icon: <AppstoreOutlined />,
  },
  {
    label: "Theo dõi quá trình & phòng của tôi",
    key: "theo_doi",
    icon: <ProfileOutlined />,
  },
];

export const DangKyKTXPage = () => {
  const [selectedKey, setSelectedKey] = useState("phong");

  const handleMenuClick: MenuProps["onClick"] = (e) => {
    setSelectedKey(e.key);
  };

  const renderContent = () => {
    switch (selectedKey) {
      case "phong":
        return <TatCaPhongPage />;

      case "theo_doi":
        return <TheoDoiVaPhongCuaToiPage />;
    }
  };

  return (
    <div>
      <Menu
        onClick={handleMenuClick}
        selectedKeys={[selectedKey]}
        mode="horizontal"
        items={items}
        style={{ marginBottom: 24 }}
      />
      {renderContent()}
    </div>
  );
};
