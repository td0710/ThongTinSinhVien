import { Flex, Spin } from "antd";

export const Loading = () => {
  return (
    <div
      style={{
        position: "fixed",
        inset: 0,
        backgroundColor: "rgba(0, 0, 0, 0.05)", // nền mờ mờ
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        zIndex: 9999,
      }}
    >
      <Flex vertical align="center" justify="center">
        <Spin size="large" tip="Đang tải..." />
      </Flex>
    </div>
  );
};
