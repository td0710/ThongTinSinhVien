import { BellOutlined } from "@ant-design/icons";
import { Card, Col, Flex, Row, Select, Space, Typography } from "antd";

const { Paragraph, Title } = Typography;

export const ThongBaoPage = () => {
  return (
    <Space direction="vertical" size="large" style={{ width: "100%" }}>
      <Flex>
        <Card
          style={{
            width: 150,
            height: 90,
            border: "1px solid #d9d9d9",
            boxShadow: "none",
          }}
          bodyStyle={{ padding: 0, height: "100%" }}
        >
          <Row
            align="top"
            justify="space-between"
            style={{ height: "100%", padding: 8 }}
          >
            <Col>
              <Paragraph style={{ margin: 0 }}>Thông báo</Paragraph>
              <Title level={5} style={{ margin: 0 }}>
                100
              </Title>
            </Col>
            <Col>
              <BellOutlined style={{ fontSize: 50, color: "#1890ff" }} />
            </Col>
          </Row>
        </Card>
      </Flex>
      <Card
        style={{
          width: "100%",
          height: 90,
          border: "1px solid #d9d9d9",
          boxShadow: "none",
        }}
      >
        <Select
          defaultValue="all"
          style={{ width: 200 }}
          options={[
            { value: "all", label: "Tất cả" },
            { value: "read", label: "Đã đọc" },
            { value: "unread", label: "Chưa đọc" },
          ]}
        />
      </Card>
    </Space>
  );
};
