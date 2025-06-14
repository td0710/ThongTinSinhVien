// layouts/MainLayout.tsx
import React, { ReactNode, useState } from "react";
import { Grid, Layout, theme } from "antd";
import Sidebar from "../components/Sidebar";
import HeaderBar from "../components/Header";

const { useBreakpoint } = Grid;
const { Sider, Header, Content } = Layout;
interface LayoutProps {
  children?: ReactNode;
}
const MainLayout: React.FC<LayoutProps> = ({ children }) => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const toggleCollapsed = () => setCollapsed(!collapsed);
  const screens = useBreakpoint();
  const isMobile = !screens.md;

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
          {children}
        </Content>
      </Layout>
    </Layout>
  );
};

export default MainLayout;
