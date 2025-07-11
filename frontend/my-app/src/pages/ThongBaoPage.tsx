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
  Form,
  Button,
} from "antd";
import axios, { AxiosError } from "axios";
import dayjs from "dayjs";
import { useEffect, useState } from "react";
import { ThongBaoModel } from "../models/ThongBaoModel";
const { Paragraph, Title, Text } = Typography;
const { RangePicker } = DatePicker;
const { Search } = Input;

const dateFormat = "YYYY/MM/DD";

export const ThongBaoPage = () => {
  const [thongBaoList, setThongBaoList] = useState<ThongBaoModel[]>([]);
  const [form] = Form.useForm();

  useEffect(() => {
    const fetchThongBaoData = async () => {
      const url = `http://localhost:8080/api/thongbao/get-all`;
      const response = await axios.get(url, {
        withCredentials: true,
      });
      const thongBaoList = response.data.map((item: any) => {
        return {
          tieuDe: item.tieuDe,
          nguoiDang: item.nguoiDang,
          ngayDang: dayjs(item.ngayDang).format(dateFormat),
          noiDung: item.noiDung,
          danhSachFileDinhKem: item.danhSachFileDinhKem
            ? item.danhSachFileDinhKem
            : null,
        };
      });
      setThongBaoList(thongBaoList);
    };
    fetchThongBaoData();
  }, []);

  const timKiem = async (data: any) => {
    try {
      const response = await axios.put(
        `http://localhost:8080/api/secure/thongtincanhan/`,
        data,
        { withCredentials: true }
      );
    } catch (error) {
      let errorMessage = "Lỗi khi tìm kiếm.";
      if (axios.isAxiosError(error)) {
        const axiosError = error as AxiosError<{ message?: string }>;
        errorMessage =
          axiosError.response?.data?.message || "Lỗi kết nối đến server.";
      }

      console.error("Lỗi khi tìm kiếm", error);
    }
  };
  const handleSubmit = async () => {
    const data = {
      ...form.getFieldsValue(),
    };
    console.log(data);
    await timKiem(data);
  };
  const [selectedItem, setSelectedItem] = useState<ThongBaoModel | null>(null);

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
        <Form form={form} onFinish={handleSubmit}>
          <Row gutter={[16, 16]} align="bottom">
            <Col xs={24} sm={12} md={6} lg={4}>
              <Form.Item name="dayStart">
                <DatePicker
                  placeholder="Từ ngày"
                  format={dateFormat}
                  style={{ width: "100%" }}
                />
              </Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6} lg={4}>
              <Form.Item name="dayEnd">
                <DatePicker
                  placeholder="Đến ngày"
                  format={dateFormat}
                  style={{ width: "100%" }}
                />
              </Form.Item>
            </Col>

            <Col xs={24} sm={24} md={12} lg={8}>
              <Form.Item name="search">
                <Input placeholder="Tìm kiếm theo tiêu đề" />
              </Form.Item>
            </Col>

            <Col
              xs={24}
              sm={12}
              md={6}
              lg={4}
              style={{ display: "flex", justifyContent: "flex-start" }}
            >
              <Form.Item>
                <Button
                  type="primary"
                  htmlType="submit"
                  size="middle"
                  style={{ minWidth: 100 }}
                >
                  Tìm kiếm
                </Button>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Card>
      {selectedItem ? (
        <Card
          title={selectedItem.tieuDe}
          extra={
            <a onClick={() => setSelectedItem(null)}>← Quay lại danh sách</a>
          }
          style={{ width: "100%", border: "1px solid #d9d9d9" }}
        >
          <Space direction="vertical" size="middle" style={{ width: "100%" }}>
            <Paragraph>
              <Text strong>Người gửi:</Text> {selectedItem?.nguoiDang}
            </Paragraph>
            <Paragraph>
              <Text strong>Ngày gửi:</Text> {selectedItem?.ngayDang}
            </Paragraph>
            <Paragraph>{selectedItem?.noiDung}</Paragraph>

            {selectedItem?.danhSachFileDinhKem &&
              selectedItem.danhSachFileDinhKem.map((file, index) => (
                <Paragraph key={index}>
                  📎{" "}
                  <a
                    href={file.duongDan}
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    {file.tenFile}
                  </a>
                </Paragraph>
              ))}
            {selectedItem?.nguoiDang && (
              <div style={{ textAlign: "right", marginTop: 32 }}>
                <Paragraph italic>
                  Ký tên: <Text strong>{selectedItem.nguoiDang}</Text>
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
            dataSource={thongBaoList}
            renderItem={(item) => (
              <List.Item
                onClick={() => setSelectedItem(item)}
                style={{ cursor: "pointer" }}
              >
                <Title level={5} style={{ marginBottom: 4 }}>
                  {item.tieuDe}
                </Title>
                <Text type="secondary">Người gửi: {item.nguoiDang}</Text>
                <br />
                <Text type="secondary">Ngày gửi: {item.ngayDang}</Text>
              </List.Item>
            )}
          />
        </Card>
      )}
    </Space>
  );
};
