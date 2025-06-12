// layouts/MainLayout.tsx
import React, { useState } from "react";
import { Grid, Layout, theme } from "antd";
import Sidebar from "../components/Sidebar";
import HeaderBar from "../components/Header";
const { useBreakpoint } = Grid;
const { Sider, Header, Content } = Layout;

const MainLayout: React.FC = () => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const toggleCollapsed = () => setCollapsed(!collapsed);
  const screens = useBreakpoint(); // 👈 lấy thông tin responsive
  const isMobile = !screens.md; // 👈 nhỏ hơn md thì coi là mobile
  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Sider
        trigger={null}
        collapsible
        collapsed={collapsed}
        breakpoint="md"
        collapsedWidth={isMobile ? 0 : 80}
      >
        <Sidebar collapsed={collapsed} />
      </Sider>
      <Layout>
        <Header style={{ padding: 0, background: colorBgContainer }}>
          <HeaderBar
            collapsed={collapsed}
            toggleCollapsed={toggleCollapsed}
            background={colorBgContainer}
          />
        </Header>
        <Content
          style={{
            margin: "24px 16px",
            padding: 24,
            minHeight: 280,
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
          }}
        >
          Content
        </Content>
      </Layout>
    </Layout>
  );
};

export default MainLayout;
