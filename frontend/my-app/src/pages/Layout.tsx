// layouts/MainLayout.tsx
import React, { ReactNode, useState } from "react";
import { Drawer, Grid, Layout, theme } from "antd";
import Sidebar from "../components/Sidebar";
import HeaderBar from "../components/Header";

const { useBreakpoint } = Grid;
const { Sider, Header, Content } = Layout;
interface LayoutProps {
  children?: ReactNode;
}
const MainLayout: React.FC<LayoutProps> = ({ children }) => {
  const [collapsed, setCollapsed] = useState(false);
  const [drawerVisible, setDrawerVisible] = useState(false);

  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const toggleCollapsed = () => {
    if (isMobile) {
      setDrawerVisible(!drawerVisible);
    } else {
      setCollapsed(!collapsed);
    }
  };
  const screens = useBreakpoint();
  const isMobile = !screens.md;

  return (
    <Layout style={{ minHeight: "100vh" }}>
      {isMobile ? (
        <Drawer
          closable={false}
          placement="left"
          onClose={() => setDrawerVisible(false)}
          open={drawerVisible}
          width={240}
          styles={{
            body: {
              padding: 0,
              backgroundColor: "#001529",
              color: "#fff",
            },
          }}
        >
          <Sidebar collapsed={false} />
        </Drawer>
      ) : (
        <Sider
          width={275}
          collapsedWidth={80}
          collapsible
          collapsed={collapsed}
          trigger={null}
          style={{ height: "100vh", position: "sticky", top: 0 }}
        >
          <Sidebar collapsed={collapsed} />
        </Sider>
      )}

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
