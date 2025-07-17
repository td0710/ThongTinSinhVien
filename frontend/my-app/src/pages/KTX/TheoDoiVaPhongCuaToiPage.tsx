import { Button, Card, Col, Row, Typography, Tag, Flex, Space } from "antd";
const { Title, Paragraph, Text } = Typography;
export const TheoDoiVaPhongCuaToiPage = () => {
  const phong = {
    tenPhong: "Phòng A101",
    loaiPhong: "4 người",
    gia: 1200000,
    soSv: 4,
    soLuongDaDangKy: 2,
    tienIchList: [
      { id: 1, tenTienIch: "Máy lạnh" },
      { id: 2, tenTienIch: "Wifi" },
    ],
  };
  const yeuCauList = [
    {
      id: 1,
      loai: "Yêu cầu đổi phòng",
      phong: {
        tenPhong: "B203",
        loaiPhong: "4 người",
        gia: 1200000,
        soLuongDaDangKy: 2,
        soSv: 4,
        tienIchList: ["Máy lạnh", "Wifi"],
        hinhAnh:
          "https://www.houzlook.com/assets/images/upload/Rooms/Bed%20Rooms/Malson%20Modern%20Bed%20Room-20180819090641741.jpg",
      },
      trangThai: "Chờ duyệt",
      tagColor: "processing",
    },
    {
      id: 2,
      loai: "Yêu cầu đăng ký phòng mới",
      phong: {
        tenPhong: "C102",
        loaiPhong: "6 người",
        gia: 1000000,
        soLuongDaDangKy: 5,
        soSv: 6,
        tienIchList: ["Quạt", "Truyền hình"],
        hinhAnh:
          "https://cdn.nhadatmoi.net/news/wp-content/uploads/2020/12/phong-tro-dep-1.jpg",
      },
      trangThai: "Đã gửi",
      tagColor: "success",
    },
  ];

  const tagColorByLoai: Record<string, string> = {
    "4 người": "blue",
    "6 người": "green",
  };

  const iconByTienIch: Record<string, React.ReactNode> = {
    "Máy lạnh": <i className="fas fa-snowflake" />,
    Wifi: <i className="fas fa-wifi" />,
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
              <Card type="inner" title={yeuCau.loai} size="default">
                <div
                  style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "flex-start",
                    gap: 16,
                  }}
                >
                  <div style={{ flex: 1 }}>
                    <div style={{ marginBottom: 8 }}>
                      <Text strong style={{ fontSize: 16 }}>
                        🏠 {yeuCau.phong.tenPhong}
                      </Text>{" "}
                      <Tag color="blue" style={{ marginLeft: 4 }}>
                        {yeuCau.phong.loaiPhong}
                      </Tag>
                    </div>

                    <Paragraph style={{ marginBottom: 4 }}>
                      <Text type="secondary">Giá:</Text>{" "}
                      <Text strong>
                        {yeuCau.phong.gia.toLocaleString()} đ/tháng
                      </Text>{" "}
                      • <Text type="secondary">Sức chứa:</Text>{" "}
                      <Text strong>
                        {yeuCau.phong.soLuongDaDangKy}/{yeuCau.phong.soSv} SV
                      </Text>
                    </Paragraph>

                    <Space wrap size={8}>
                      {yeuCau.phong.tienIchList.map((tienIch, index) => (
                        <Tag key={index} color="default">
                          {tienIch}
                        </Tag>
                      ))}
                    </Space>
                  </div>

                  <div style={{ textAlign: "right", minWidth: 120 }}>
                    <Space size="small">
                      <Tag color={yeuCau.tagColor}>{yeuCau.trangThai}</Tag>
                      <Button danger size="small">
                        Hủy yêu cầu
                      </Button>
                    </Space>
                  </div>
                </div>
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
            alt={phong.tenPhong}
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
              🏠 <Text strong>{phong.tenPhong}</Text>
            </Title>
            <Tag color={tagColorByLoai[phong.loaiPhong] || "default"}>
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

          <Col span={24}>
            <Space wrap size={8}>
              {phong.tienIchList.map((tienIch) => (
                <Tag
                  key={tienIch.id}
                  color="default"
                  icon={iconByTienIch[tienIch.tenTienIch]}
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
