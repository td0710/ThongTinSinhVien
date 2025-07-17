import {
  ApartmentOutlined,
  CloudOutlined,
  FireOutlined,
  InboxOutlined,
  RestOutlined,
  SkinOutlined,
  WifiOutlined,
} from "@ant-design/icons";
import { Button, Card, Col, Row, Typography, Tag, Flex, Space } from "antd";
import { YeuCauKTXModel } from "../../models/YeuCauKTXModel";
import { use, useEffect, useState } from "react";
import { TienIchModel } from "../../models/TienIchModel";
import axios from "axios";
import { PhongModel } from "../../models/PhongModel";
const { Title, Paragraph, Text } = Typography;
export const TheoDoiVaPhongCuaToiPage = () => {
  const [yeuCauList, setYeuCauList] = useState<YeuCauKTXModel[]>([]);
  const [phongCuaToi, setPhongCuaToi] = useState<PhongModel>();
  const fetchYeuCauList = async () => {
    try {
      const url = "http://localhost:8080/api/yeucauktx/get-by-id?id=1";

      const response = await axios.get(url);

      console.log(response);

      const danhSachYeuCau = response.data.map((item: YeuCauKTXModel) => {
        const yeuCau = new YeuCauKTXModel(
          item.id,
          item.loaiYeuCau,
          new PhongModel(
            item.phongHienTai.id,
            item.phongHienTai.tenPhong,
            item.phongHienTai.loaiPhong,
            item.phongHienTai.soSv,
            item.phongHienTai.gia,
            item.phongHienTai.soLuongDaDangKy,
            item.phongHienTai.tienIchList
          ),
          item.trangThai,
          item.phongMongMuon
            ? new PhongModel(
                item.phongMongMuon.id,
                item.phongMongMuon.tenPhong,
                item.phongMongMuon.loaiPhong,
                item.phongMongMuon.soSv,
                item.phongMongMuon.gia,
                item.phongMongMuon.soLuongDaDangKy,
                item.phongMongMuon.tienIchList
              )
            : undefined
        );
        return yeuCau;
      });
      console.log(danhSachYeuCau);
      setYeuCauList(danhSachYeuCau);
    } catch (error) {
      console.error("Error fetching yêu cầu KTX:", error);
    }
  };
  const fetchPhongCuaToi = async () => {
    try {
      const url = `${process.env.REACT_APP_API_BASE_URL}/secure/phong/phong-hien-tai`;

      const response = await axios.get(url, {
        withCredentials: true,
      });

      console.log(response);

      setPhongCuaToi(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchPhongCuaToi();
    fetchYeuCauList();
  }, []);

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

  const tagColorByTrangThai = {
    "Đang tiếp nhận": "processing",
    "Đã tiếp nhận": "blue",
    "Hoàn thành": "green",
    "Từ chối": "red",
  };
  return (
    <div style={{ padding: 24 }}>
      <Card
        title="Theo dõi các yêu cầu của bạn"
        style={{ width: "100%", border: "1px solid #d9d9d9" }}
        bodyStyle={{ padding: 16 }}
        hoverable
      >
        <Row gutter={[16, 16]}>
          {yeuCauList.map((yeuCau) => (
            <Col span={24} key={yeuCau.id}>
              <Card type="inner" title={yeuCau.loaiYeuCau} size="default">
                <Row gutter={[16, 16]} align="top">
                  <Col xs={24} md={yeuCau.loaiYeuCau === "Đổi phòng" ? 12 : 20}>
                    <Row align="middle" wrap={false}>
                      <Text strong style={{ fontSize: 16 }}>
                        🏠{" "}
                        {yeuCau.loaiYeuCau === "Đổi phòng"
                          ? yeuCau.phongHienTai.tenPhong +
                            " ➡️ " +
                            yeuCau.phongMongMuon?.tenPhong
                          : yeuCau.phongHienTai.tenPhong}
                      </Text>

                      <Tag
                        color={
                          tagColorByLoai[
                            (yeuCau.loaiYeuCau == "Đổi phòng"
                              ? yeuCau.phongMongMuon?.loaiPhong
                              : yeuCau.phongHienTai
                                  .loaiPhong) as keyof typeof tagColorByLoai
                          ] || "default"
                        }
                        style={{ marginLeft: 8 }}
                      >
                        {yeuCau.loaiYeuCau === "Đổi phòng"
                          ? yeuCau.phongMongMuon?.loaiPhong
                          : yeuCau.phongHienTai.loaiPhong}
                      </Tag>
                    </Row>

                    <Paragraph style={{ marginTop: 8, marginBottom: 4 }}>
                      <Text type="secondary">Giá:</Text>{" "}
                      <Text strong>
                        {(yeuCau.loaiYeuCau === "Đổi phòng"
                          ? yeuCau.phongMongMuon?.gia ?? 0
                          : yeuCau.phongHienTai.gia ?? 0
                        ).toLocaleString()}{" "}
                        đ/tháng
                      </Text>{" "}
                      • <Text type="secondary">Sức chứa:</Text>{" "}
                      <Text strong>
                        {(yeuCau.loaiYeuCau === "Đổi phòng"
                          ? yeuCau.phongMongMuon?.soLuongDaDangKy
                          : yeuCau.phongHienTai.soLuongDaDangKy) +
                          "/" +
                          (yeuCau.loaiYeuCau === "Đổi phòng"
                            ? yeuCau.phongMongMuon?.soSv
                            : yeuCau.phongHienTai.soSv)}{" "}
                        SV
                      </Text>
                    </Paragraph>

                    <Space wrap size={8}>
                      {(yeuCau.loaiYeuCau === "Đổi phòng"
                        ? yeuCau.phongMongMuon?.tienIchList
                        : yeuCau.phongHienTai.tienIchList
                      )?.map((tienIch: TienIchModel) => (
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

                  <Col
                    xs={24}
                    md={yeuCau.loaiYeuCau === "Đổi phòng" ? 12 : 4}
                    style={{ textAlign: "right" }}
                  >
                    <Space
                      direction="vertical"
                      size="small"
                      style={{ width: "100%", alignItems: "flex-end" }}
                    >
                      <Tag
                        color={
                          tagColorByTrangThai[
                            yeuCau.trangThai as keyof typeof tagColorByTrangThai
                          ] || "default"
                        }
                      >
                        {yeuCau.trangThai}
                      </Tag>
                      <Button danger size="small">
                        Hủy yêu cầu
                      </Button>
                    </Space>
                  </Col>
                </Row>
              </Card>
            </Col>
          ))}
        </Row>
      </Card>

      <Title level={3} style={{ textAlign: "left", marginBottom: 24 }}>
        Phòng của tôi
      </Title>

      <Card
        hoverable
        style={{
          width: "100%",
          border: "1px solid #d9d9d9",
          marginBottom: 24,
        }}
        cover={
          <img
            alt={phongCuaToi?.tenPhong || "Phòng của tôi"}
            src="https://www.houzlook.com/assets/images/upload/Rooms/Bed%20Rooms/Malson%20Modern%20Bed%20Room-20180819090641741.jpg"
            style={{
              height: 500,
              objectFit: "cover",
            }}
          />
        }
        bodyStyle={{ padding: 16 }}
      >
        <Row gutter={[8, 16]} justify="space-between" align="top">
          <Col span={24}>
            <Title level={5} style={{ marginTop: 8, marginBottom: 4 }}>
              🏠 <Text strong>{phongCuaToi?.tenPhong}</Text>
            </Title>
            <Tag
              color={
                tagColorByLoai[
                  phongCuaToi?.loaiPhong as keyof typeof tagColorByLoai
                ] || "default"
              }
            >
              {phongCuaToi?.loaiPhong}
            </Tag>
          </Col>

          <Col span={24}>
            <Paragraph style={{ marginBottom: 4 }}>
              <Text type="secondary">Giá:</Text>{" "}
              <Text strong>{phongCuaToi?.gia.toLocaleString()} đ/tháng</Text>
            </Paragraph>
            <Paragraph style={{ marginBottom: 4 }}>
              <Text type="secondary">Sức chứa:</Text>{" "}
              <Text strong>
                {phongCuaToi?.soLuongDaDangKy}/{phongCuaToi?.soSv} SV
              </Text>
            </Paragraph>
          </Col>

          <Col span={24}>
            <Space wrap size={8}>
              {phongCuaToi?.tienIchList.map((tienIch: TienIchModel) => (
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
            <Flex justify="end">
              <Button danger type="primary">
                Hủy đăng ký phòng
              </Button>
            </Flex>
          </Col>
        </Row>
      </Card>
    </div>
  );
};
