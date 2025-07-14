import {
  ApartmentOutlined,
  CloudOutlined,
  FireOutlined,
  InboxOutlined,
  RestOutlined,
  SkinOutlined,
  WifiOutlined,
} from "@ant-design/icons";
import {
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
  Pagination,
  Slider,
  Switch,
} from "antd";
import { useEffect, useState } from "react";
import { PhongModel } from "../../models/PhongModel";
import axios from "axios";
import { TienIchModel } from "../../models/TienIchModel";
const { Option } = Select;
const { Paragraph, Title, Text } = Typography;

const PAGE_SIZE = 6;

export const TatCaPhongPage = () => {
  const [totalItems, setTotalItems] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [form] = Form.useForm();
  const [phongList, setPhongList] = useState<PhongModel[]>([]);

  const tagColorByLoai = {
    "Cơ bản": "default",
    "Cơ bản nhỏ": "gray",
    "Thiết bị tăng cường": "green",
    "Phổ thông không điều hòa": "volcano",
    "Phổ thông có điều hòa": "orange",
    "Tiêu chuẩn": "blue",
  };
  const iconByTienIch = {
    "Vệ sinh khép kín": <RestOutlined />,
    "Giường tầng": <ApartmentOutlined />,
    "Bình nước nóng": <FireOutlined />,
    Internet: <WifiOutlined />,
    "Điều hòa": <CloudOutlined />,
    "Tủ quần áo": <SkinOutlined />,
    "Tủ giày": <InboxOutlined />,
  };
  const fetchPhongList = async () => {
    try {
      const url = `${
        process.env.REACT_APP_API_BASE_URL
      }/secure/phong/get-all?page=${currentPage - 1}&size=${PAGE_SIZE}`;
      const response = await axios.get(url, {
        withCredentials: true,
      });
      const data = response.data;
      const phongList = response.data.phong.map((item: any) => {
        return new PhongModel(
          item.id,
          item.tenPhong,
          item.loaiPhong,
          item.soSv,
          item.gia,
          item.soLuongDaDangKy,
          item.tienIchList.map(
            (tienIch: any) => new TienIchModel(tienIch.id, tienIch.tenTienIch)
          )
        );
      });
      setPhongList(phongList);
      setTotalItems(data.totalElements);
    } catch (error) {
      console.error("Error fetching phong list:", error);
      setPhongList([]);
      setTotalItems(0);
    }
  };
  useEffect(() => {
    fetchPhongList();
  }, [currentPage]);

  const handleTimKiem = async (values: any) => {
    const url = `${
      process.env.REACT_APP_API_BASE_URL
    }/secure/phong/get-by-search?page=${currentPage - 1}&size=${PAGE_SIZE}`;
    const response = await axios.post(url, values, {
      withCredentials: true,
    });
    const data = response.data;
    const phongList = data.phong.map((item: any) => {
      return new PhongModel(
        item.id,
        item.tenPhong,
        item.loaiPhong,
        item.soSv,
        item.gia,
        item.soLuongDaDangKy,
        item.tienIchList.map(
          (tienIch: any) => new TienIchModel(tienIch.id, tienIch.tenTienIch)
        )
      );
    });
    setCurrentPage(1);
    setPhongList(phongList);
    setTotalItems(data.totalElements);
  };
  const handleSubmit = async () => {
    const gia = form.getFieldValue("gia");
    const data = {
      ten: form.getFieldValue("ten"),
      loaiPhong: form.getFieldValue("loaiPhong"),
      soSv: form.getFieldValue("soSv"),
      start: gia[0],
      end: gia[1],
      trong: form.getFieldValue("trong"),
    };
    await handleTimKiem(data);
  };
  console.log("Phong List:", phongList);
  return (
    <Space direction="vertical" size="large" style={{ width: "100%" }}>
      <Card hoverable style={{ border: "1px solid #d9d9d9" }}>
        <Form
          form={form}
          layout="vertical"
          onFinish={handleSubmit}
          initialValues={{
            gia: [500000, 2000000],
            trong: false,
          }}
        >
          <Row gutter={[16, 16]}>
            <Col xs={24} sm={12} md={6}>
              <Form.Item name="ten" label="Tên phòng">
                <Input placeholder="Nhập tên phòng" allowClear />
              </Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6}>
              <Form.Item name="loaiPhong" label="Loại phòng">
                <Select placeholder="Chọn loại phòng" allowClear>
                  <Option value="Cơ bản">Cơ bản</Option>
                  <Option value="Cơ bản nhỏ">Cơ bản nhỏ</Option>
                  <Option value="Thiết bị tăng cường">
                    Thiết bị tăng cường
                  </Option>
                  <Option value="Phổ thông không điều hòa">
                    Phổ thông không điều hòa
                  </Option>
                  <Option value="Phổ thông có điều hòa">
                    Phổ thông có điều hòa
                  </Option>
                  <Option value="Tiêu chuẩn">Tiêu chuẩn</Option>
                </Select>
              </Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6}>
              <Form.Item name="soSv" label="Số sinh viên">
                <Select placeholder="Số SV / phòng" allowClear>
                  <Option value={2}>2</Option>
                  <Option value={4}>4</Option>
                  <Option value={6}>6</Option>
                  <Option value={8}>8</Option>
                </Select>
              </Form.Item>
            </Col>

            <Col xs={24} sm={24} md={6}>
              <Form.Item name="gia" label="Khoảng giá (VNĐ)">
                <Slider
                  range
                  step={50000}
                  min={500000}
                  max={2000000}
                  tooltip={{ formatter: (v: any) => `${v.toLocaleString()} đ` }}
                />
              </Form.Item>
            </Col>

            <Col xs={24} sm={12} md={6}>
              <Form.Item
                name="trong"
                label="Chỉ còn phòng trống"
                valuePropName="checked"
              >
                <Switch />
              </Form.Item>
            </Col>

            <Col
              xs={24}
              sm={12}
              md={6}
              style={{ display: "flex", alignItems: "flex-end" }}
            >
              <Form.Item>
                <Button type="primary" htmlType="submit" block>
                  Tìm kiếm
                </Button>
              </Form.Item>
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
                  alt={phong.tenPhong}
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
                    🏠 <Text strong>{phong.tenPhong}</Text>
                  </Title>
                  <Tag
                    color={
                      tagColorByLoai[
                        phong.loaiPhong as keyof typeof tagColorByLoai
                      ] || "default"
                    }
                  >
                    {phong.loaiPhong}
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
                      {phong.soLuongDaDangKy}/{phong.soSv} SV
                    </Text>
                  </Paragraph>
                </Col>

                <Col
                  span={24}
                  style={{
                    height: 48,
                  }}
                >
                  <Space wrap size={8}>
                    {phong.tienIchList?.map((tienIch: TienIchModel) => (
                      <Tag
                        key={tienIch.id}
                        color="default"
                        icon={
                          iconByTienIch[
                            tienIch.tenTienIch as keyof typeof iconByTienIch
                          ]
                        }
                      >
                        {tienIch.tenTienIch}
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
