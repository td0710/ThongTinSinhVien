import { BellOutlined } from "@ant-design/icons";
import {
  Card,
  Col,
  DatePicker,
  Flex,
  Row,
  Space,
  Typography,
  Input,
  List,
  Form,
  Button,
  Pagination,
} from "antd";
import axios, { AxiosError } from "axios";
import dayjs from "dayjs";
import { useEffect, useState } from "react";
import { ThongBaoModel } from "../models/ThongBaoModel";
const { Paragraph, Title, Text } = Typography;

const dateFormat = "YYYY/MM/DD";
const PAGE_SIZE = 3;
export const ThongBaoPage = () => {
  const [thongBaoList, setThongBaoList] = useState<ThongBaoModel[]>([]);
  const [totalThongBao, setTotalThongBao] = useState<number>(0);
  const [form] = Form.useForm();
  const [totalItems, setTotalItems] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);

  const fetchThongBao = async () => {
    try {
      const url = `${
        process.env.REACT_APP_API_BASE_URL
      }/secure/thongbao/get-all?page=${currentPage - 1}&size=${PAGE_SIZE}`;
      const response = await axios.get(url, {
        withCredentials: true,
      });

      console.log("Response data:", response.data);
      const thongBaoList = response.data.thongBao.map((item: any) => {
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
      setTotalItems(response.data.totalElements);
      setThongBaoList(thongBaoList);
    } catch (error) {}
  };
  const fetchTotalThongBao = async () => {
    try {
      const response = await axios.get(
        `${process.env.REACT_APP_API_BASE_URL}/secure/thongbao/total`,
        { withCredentials: true }
      );
      console.log("Tổng số thông báo:", response.data);
      setTotalThongBao(response.data);
    } catch (error) {
      console.error("Lỗi khi lấy tổng số thông báo", error);
    }
  };
  useEffect(() => {
    fetchThongBao();
    fetchTotalThongBao();
  }, [currentPage]);

  const handleTimKiem = async (data: any) => {
    try {
      const response = await axios.post(
        `${process.env.REACT_APP_API_BASE_URL}/secure/thongbao/search?page=${
          currentPage - 1
        }&size=${PAGE_SIZE}`,
        data,
        { withCredentials: true }
      );
      const thongBaoList = response.data.thongBao.map((item: any) => {
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
      setCurrentPage(1);
      setTotalItems(response.data.totalElements);
      setThongBaoList(thongBaoList);
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
    const raw = form.getFieldsValue();

    const data = {
      ...raw,
      startDate: raw.startDate
        ? dayjs(raw.startDate).startOf("day").format("YYYY-MM-DDTHH:mm:ss")
        : null,
      endDate: raw.endDate
        ? dayjs(raw.endDate).endOf("day").format("YYYY-MM-DDTHH:mm:ss")
        : null,
    };

    await handleTimKiem(data);
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
          hoverable
          bodyStyle={{ padding: 0, height: "100%" }}
        >
          <Row
            align="top"
            justify="space-between"
            style={{ height: "100%", padding: 8 }}
          >
            <Col>
              <Paragraph style={{ margin: 0, fontSize: 13 }}>
                Số thông báo
              </Paragraph>{" "}
              <Title level={5} style={{ margin: 0 }}>
                {totalThongBao}
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
        }}
        hoverable
      >
        <Form form={form} onFinish={handleSubmit}>
          <Row gutter={[16, 16]} align="bottom">
            <Col xs={24} sm={12} md={6} lg={4}>
              <Form.Item name="startDate">
                <DatePicker
                  placeholder="Từ ngày"
                  format={dateFormat}
                  style={{ width: "100%" }}
                />
              </Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6} lg={4}>
              <Form.Item name="endDate">
                <DatePicker
                  placeholder="Đến ngày"
                  format={dateFormat}
                  style={{ width: "100%" }}
                />
              </Form.Item>
            </Col>

            <Col xs={24} sm={24} md={12} lg={8}>
              <Form.Item name="tieuDe">
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
          hoverable
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
          hoverable
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
      <Pagination
        align="center"
        current={currentPage}
        pageSize={PAGE_SIZE}
        total={totalItems}
        onChange={(page) => setCurrentPage(page)}
      ></Pagination>
    </Space>
  );
};
