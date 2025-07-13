import {
  Badge,
  Button,
  Card,
  Col,
  Flex,
  Form,
  Input,
  Row,
  Select,
  Space,
  Tag,
  Typography,
} from "antd";
import { useState } from "react";
const { Option } = Select;
const { Paragraph, Title, Text } = Typography;
export const TatCaPhongPage = () => {
  const [form] = Form.useForm();
  const [phongList, setPhongList] = useState([
    {
      id: 1,
      ten: "Phòng A101",
      loai: "Cơ bản",
      trangThai: "Còn trống",
      gia: 300000,
      soLuongSV: 4,
      soSVDaDK: 2,
      tienIch: ["Vệ sinh khép kín", "Giường tầng", "Internet"],
    },
    {
      id: 2,
      ten: "Phòng A102",
      loai: "Cơ bản nhỏ",
      trangThai: "Còn trống",
      gia: 225000,
      soLuongSV: 4,
      soSVDaDK: 1,
      tienIch: ["Vệ sinh khép kín", "Giường tầng", "Internet"],
    },
    {
      id: 3,
      ten: "Phòng B201",
      loai: "Thiết bị tăng cường",
      trangThai: "Còn trống",
      gia: 625000,
      soLuongSV: 6,
      soSVDaDK: 4,
      tienIch: [
        "Vệ sinh khép kín",
        "Giường tầng",
        "Máy lạnh",
        "Bình nước nóng",
        "Internet",
      ],
    },
    {
      id: 4,
      ten: "Phòng B202",
      loai: "Phổ thông không điều hòa",
      trangThai: "Còn trống",
      gia: 1140000,
      soLuongSV: 4,
      soSVDaDK: 2,
      tienIch: [
        "Vệ sinh khép kín",
        "Giường tầng",
        "Bình nước nóng",
        "Internet",
      ],
    },
    {
      id: 5,
      ten: "Phòng C301",
      loai: "Phổ thông có điều hòa",
      trangThai: "Đang sử dụng",
      gia: 1245000,
      soLuongSV: 4,
      soSVDaDK: 4,
      tienIch: [
        "Vệ sinh khép kín",
        "Giường tầng",
        "Bình nước nóng",
        "Điều hòa",
        "Internet",
      ],
    },
    {
      id: 6,
      ten: "Phòng C302",
      loai: "Tiêu chuẩn",
      trangThai: "Còn trống",
      gia: 1275000,
      soLuongSV: 4,
      soSVDaDK: 1,
      tienIch: [
        "Vệ sinh khép kín",
        "Giường tầng",
        "Bình nước nóng",
        "Điều hòa",
        "Tủ quần áo",
        "Tủ giày",
        "Internet",
      ],
    },
  ]);

  const tagColorByLoai = {
    "Cơ bản": "default",
    "Cơ bản nhỏ": "gray",
    "Thiết bị tăng cường": "green",
    "Phổ thông không điều hòa": "volcano",
    "Phổ thông có điều hòa": "orange",
    "Tiêu chuẩn": "blue",
  };
  const handleSubmit = (values: any) => {
    console.log("Filter values:", values);
    // Gọi API lọc ở đây nếu cần
  };
  return (
    <Space direction="vertical" size="large" style={{ width: "100%" }}>
      {/* Thanh lọc dữ liệu */}
      <Card hoverable style={{ border: "1px solid #d9d9d9" }}>
        <Form form={form} onFinish={handleSubmit}>
          <Row gutter={[16, 16]}>
            <Col xs={24} sm={12} md={6}>
              <Form.Item name="ngay"></Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6}>
              <Form.Item name="ten">
                <Input placeholder="Tìm theo tên phòng" />
              </Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6}>
              <Form.Item name="loai">
                <Select placeholder="Loại phòng">
                  <Option value="VIP">VIP</Option>
                  <Option value="Thường">Thường</Option>
                </Select>
              </Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6}>
              <Button type="primary" htmlType="submit" block>
                Tìm kiếm
              </Button>
            </Col>
          </Row>
        </Form>
      </Card>

      <Row gutter={[16, 16]}>
        {phongList.map((phong: any) => (
          <Col xs={24} sm={12} md={12} lg={8} key={phong.id}>
            <Card
              hoverable
              style={{
                width: "100%",
                border: "1px solid #d9d9d9",
                height: "100%",
              }}
              cover={
                <img
                  alt={phong.ten}
                  src="https://www.houzlook.com/assets/images/upload/Rooms/Bed%20Rooms/Malson%20Modern%20Bed%20Room-20180819090641741.jpg"
                  style={{
                    height: 180,
                    objectFit: "cover",
                  }}
                />
              }
              bodyStyle={{ padding: 12 }}
            >
              <Row gutter={[8, 16]} justify={"space-between"} align="top">
                <Col span={24}>
                  <Title level={5} style={{ marginTop: 8, marginBottom: 4 }}>
                    🏠 <Text strong>{phong.ten}</Text>
                  </Title>
                  <Tag
                    color={
                      tagColorByLoai[
                        phong.loai as keyof typeof tagColorByLoai
                      ] || "default"
                    }
                  >
                    {phong.loai}
                  </Tag>
                </Col>

                <Col span={24}>
                  <Paragraph style={{ marginBottom: 4 }}>
                    <Text type="secondary">Giá:</Text>{" "}
                    <Text strong>{phong.gia.toLocaleString()} đ/tháng</Text>
                  </Paragraph>
                  <Paragraph style={{ marginBottom: 4 }}>
                    <Text type="secondary">Sức chứa:</Text>{" "}
                    <Text strong>
                      {phong.soSVDaDK}/{phong.soLuongSV} SV
                    </Text>
                  </Paragraph>
                </Col>

                <Col
                  span={24}
                  style={{
                    height: 48, // Chiều cao cố định cho tags
                  }}
                >
                  <Space wrap size={8}>
                    {phong.tienIch?.map((item: any, idx: number) => (
                      <Tag key={idx} color="default">
                        {item}
                      </Tag>
                    ))}
                  </Space>
                </Col>

                <Col span={24}>
                  <Flex>
                    <Button
                      color="primary"
                      size="middle"
                      block
                      variant="filled"
                      // onClick={() => handleDangKy(phong)}
                    >
                      Đăng ký
                    </Button>
                  </Flex>
                </Col>
              </Row>
            </Card>
          </Col>
        ))}
      </Row>
    </Space>
  );
};
