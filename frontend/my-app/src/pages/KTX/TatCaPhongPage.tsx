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

  const fetchPhongList = async () => {
    try {
      const url = `http://localhost:8080/api/secure/phong/get-all?page=${
        currentPage - 1
      }&size=${PAGE_SIZE}`;
      const response = await axios.get(url, {
        withCredentials: true,
      });
      const data = response.data;
      console.log(data);
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
      console.log(phongList);
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
  const handleSubmit = (values: any) => {
    console.log("Filter values:", values);
  };
  console.log("Phong List:", phongList);
  return (
    <Space direction="vertical" size="large" style={{ width: "100%" }}>
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
