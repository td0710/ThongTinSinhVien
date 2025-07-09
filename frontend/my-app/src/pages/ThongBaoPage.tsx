import { BellOutlined } from "@ant-design/icons";
import {
  Card,
  Col,
  DatePicker,
  Flex,
  Row,
  Select,
  Space,
  Typography,
  Input,
  List,
  Modal,
  Button,
} from "antd";
import dayjs from "dayjs";
import { useState } from "react";

const { Paragraph, Title, Text } = Typography;
const { RangePicker } = DatePicker;
const { Search } = Input;

const dateFormat = "YYYY/MM/DD";
interface ThongBaoItem {
  title: string;
  sender: string;
  date: string;
  content: string;
  signature?: string;
  fileUrl?: string;
}

export const ThongBaoPage = () => {
  const data: ThongBaoItem[] = [
    {
      title: "Lịch thi cuối kỳ đã được cập nhật",
      sender: "Phòng Đào tạo",
      date: "2025/07/05",
      content:
        "Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.Lịch thi học kỳ II năm học 2024–2025 đã được cập nhật trên hệ thống.",
      signature: "Nguyễn Văn A",
      fileUrl: "/files/lich-thi.pdf",
    },
  ];

  const [modalVisible, setModalVisible] = useState(false);
  const [selectedItem, setSelectedItem] = useState<ThongBaoItem | null>(null);

  const handleClick = (item: any) => {
    setSelectedItem(item);
    setModalVisible(true);
  };

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
          minHeight: 90,
          border: "1px solid #d9d9d9",
          boxShadow: "none",
        }}
      >
        <Row gutter={[16, 16]}>
          <Col xs={24} sm={12} md={12} lg={6}>
            <Select
              defaultValue="all"
              style={{ width: "100%" }}
              options={[
                { value: "all", label: "Tất cả" },
                { value: "read", label: "Đã đọc" },
                { value: "unread", label: "Chưa đọc" },
              ]}
            />
          </Col>

          <Col xs={24} sm={12} md={6} lg={4}>
            <DatePicker
              placeholder="Từ ngày"
              defaultValue={dayjs("2025/01/01", dateFormat)}
              format={dateFormat}
              style={{ width: "100%" }}
            />
          </Col>

          <Col xs={24} sm={12} md={6} lg={4}>
            <DatePicker
              placeholder="Đến ngày"
              defaultValue={dayjs("2025/01/01", dateFormat)}
              format={dateFormat}
              style={{ width: "100%" }}
            />
          </Col>

          <Col xs={24} sm={24} md={12} lg={10}>
            <Search
              placeholder="Nhập nội dung tìm kiếm"
              allowClear
              enterButton="Tìm"
              size="middle"
              style={{ width: "100%" }}
              // onSearch={onSearch}
            />
          </Col>
        </Row>
      </Card>
      {selectedItem ? (
        <Card
          title={selectedItem.title}
          extra={
            <a onClick={() => setSelectedItem(null)}>← Quay lại danh sách</a>
          }
          style={{ width: "100%", border: "1px solid #d9d9d9" }}
        >
          <Space direction="vertical" size="middle" style={{ width: "100%" }}>
            <Paragraph>
              <Text strong>Người gửi:</Text> {selectedItem?.sender}
            </Paragraph>
            <Paragraph>
              <Text strong>Ngày gửi:</Text> {selectedItem?.date}
            </Paragraph>
            <Paragraph>{selectedItem?.content}</Paragraph>

            {selectedItem?.fileUrl && (
              <Paragraph>
                📎{" "}
                <a
                  href={selectedItem?.fileUrl}
                  target="_blank"
                  rel="noopener noreferrer"
                >
                  Xem file đính kèm
                </a>
              </Paragraph>
            )}

            {selectedItem?.signature && (
              <div style={{ textAlign: "right", marginTop: 32 }}>
                <Paragraph italic>
                  Ký tên: <Text strong>{selectedItem.signature}</Text>
                </Paragraph>
              </div>
            )}
          </Space>
        </Card>
      ) : (
        <Card
          title="Danh sách thông báo"
          style={{ width: "100%", border: "1px solid #d9d9d9" }}
        >
          <List
            itemLayout="vertical"
            dataSource={data}
            renderItem={(item) => (
              <List.Item
                onClick={() => setSelectedItem(item)}
                style={{ cursor: "pointer" }}
              >
                <Title level={5} style={{ marginBottom: 4 }}>
                  {item.title}
                </Title>
                <Text type="secondary">Người gửi: {item.sender}</Text>
                <br />
                <Text type="secondary">Ngày gửi: {item.date}</Text>
              </List.Item>
            )}
          />
        </Card>
      )}
    </Space>
  );
};
