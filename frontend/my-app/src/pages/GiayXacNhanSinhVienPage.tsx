import {
  Alert,
  Card,
  Col,
  Row,
  Space,
  Table,
  Select,
  Form,
  Button,
} from "antd";
import { Typography } from "antd";
import { useEffect, useState } from "react";
import { GiayXacNhanModel } from "../models/GiayXacNhanModel";
import axios, { AxiosError } from "axios";
import { useCustomNotification } from "../components/Notification";
const { Paragraph } = Typography;
const { Option } = Select;

export const GiayXacNhanSinhVienPage = () => {
  const [giayXacNhan, setGiayXacNhan] = useState<GiayXacNhanModel[]>([]);
  const [form] = Form.useForm();
  const [dsYeuCau, setDsYeuCau] = useState([]);
  const selectedGiay = Form.useWatch("giayXacNhan", form);
  const { contextHolder, notify } = useCustomNotification();

  useEffect(() => {
    const fetchLoaiGiayXacNhan = async () => {
      const url = `http://localhost:8080/api/secure/giayxacnhan/get-all`;
      const response = await axios.get(url);
      const listGiayXacNhan = response.data.map((item: any) => {
        return {
          id: item.id,
          name: item.name,
        };
      });
      console.log(response);
      setGiayXacNhan(listGiayXacNhan);
    };
    fetchLoaiGiayXacNhan();
  }, []);
  const fetchDanhSachYeuCau = async () => {
    const url = `http://localhost:8080/api/secure/yeucaugiayxacnhan/get-all?userId=1`;
    const response = await axios.get(url);
    const list = response.data.map((item: any, index: number) => ({
      key: index + 1,
      loai: item.loaiGiay, // hoặc convert nếu là ENUM
      ngayTao: item.ngayTao?.slice(0, 10),
      trangThai: item.trangThai,
      noiNhan: item.noiNhan,
      ngayNhan: item.ngayNhan,
      ghiChu: item.ghiChu,
    }));
    setDsYeuCau(list);
  };
  useEffect(() => {
    fetchDanhSachYeuCau();
  }, []);
  const handleSubmit = async (values: any) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/secure/yeucaugiayxacnhan/create",
        {
          user_id: 1,
          loaiGiay: values.giayXacNhan,
        }
      );
      notify(
        "success",
        "Yêu cầu thành công",
        "Yêu cầu cấp giấy xác nhận sinh viên đã được gửi"
      );
      form.resetFields();
      fetchDanhSachYeuCau();
    } catch (error) {
      let errorMessage = "Đã xảy ra lỗi khi gửi yêu cầu.";
      if (axios.isAxiosError(error)) {
        const axiosError = error as AxiosError<{ message?: string }>;
        errorMessage =
          axiosError.response?.data?.message || "Lỗi kết nối đến server.";
      }

      notify("error", "Gửi yêu cầu thất bại", errorMessage);

      console.error("Lỗi khi gửi yêu cầu:", error);
    }
  };
  const columns = [
    {
      title: "STT",
      dataIndex: "key",
      key: "key",
    },
    {
      title: "Loại giấy",
      dataIndex: "loai",
      key: "loai",
    },
    {
      title: "Ngày tạo",
      dataIndex: "ngayTao",
      key: "ngayTao",
    },

    {
      title: "Trạng thái",
      dataIndex: "trangThai",
      key: "trangThai",
    },
    {
      title: "Nơi nhận",
      dataIndex: "noiNhan",
      key: "noiNhan",
    },
    {
      title: "Ngày nhận",
      dataIndex: "ngayNhan",
      key: "ngayNhan",
    },
    {
      title: "Ghi chú",
      dataIndex: "ghiChu",
      key: "ghiChu",
    },
  ];
  return (
    <>
      {contextHolder}
      <Space direction="vertical" size="large" style={{ width: "100%" }}>
        <Form form={form} onFinish={handleSubmit}>
          <Card title="Chọn loại giấy xác nhận" bordered>
            <Row gutter={[16, 16]}>
              <Col span={12}>
                <Form.Item name="giayXacNhan">
                  <Select
                    placeholder="Chọn loại giấy xác nhận"
                    // onChange={handleChange}
                    style={{ width: "100%" }}
                  >
                    {giayXacNhan.map((item: any) => (
                      <Option key={item.id} value={item.name}>
                        {item.name}
                      </Option>
                    ))}
                  </Select>
                </Form.Item>
                <Button
                  type="primary"
                  htmlType="submit"
                  disabled={!selectedGiay}
                >
                  Xin cấp giấy XNSV
                </Button>
              </Col>
            </Row>
          </Card>
        </Form>
        <Alert
          type="info"
          showIcon
          message="Hướng dẫn nhận các loại giấy xác nhận"
          description={
            <>
              <Paragraph>
                <strong>
                  • Giấy xác nhận sinh viên để nhận ưu đãi giáo dục:
                </strong>
                <br />
                Trả kết quả sau tối thiểu <strong>12h làm việc</strong>; vào{" "}
                <strong>sáng Thứ 3</strong> và <strong>chiều Thứ 5</strong> hàng
                tuần. <br />
                <em>Địa điểm nhận:</em> Tầng 1 nhà T1.
              </Paragraph>

              <Paragraph>
                <strong>
                  • Giấy xác nhận sinh viên để vay vốn tại NHCSXH địa phương:
                </strong>
                <br />
                Trả kết quả sau tối thiểu <strong>12h làm việc</strong>; vào{" "}
                <strong>chiều Thứ 3</strong> và <strong>chiều Thứ 6</strong>{" "}
                hàng tuần. <br />
                <em>Địa điểm nhận:</em> Tầng 1 nhà T1.
              </Paragraph>

              <Paragraph>
                <strong>
                  • Giấy xác nhận sinh viên để giải quyết các vấn đề cá nhân
                  khác
                </strong>{" "}
                (như: miễn trừ thuế thu nhập, xin việc, du học, …):
                <br />
                Trả kết quả sau tối thiểu <strong>12h làm việc</strong>; vào{" "}
                <strong>chiều Thứ 2</strong>, <strong>sáng Thứ 4</strong> và{" "}
                <strong>sáng Thứ 6</strong> hàng tuần. <br />
                <em>Địa điểm nhận:</em> Tầng 1 nhà T1.
              </Paragraph>

              <Paragraph>
                <strong>• Giấy giới thiệu đăng ký xe máy:</strong>
                <br />
                Trả kết quả sau tối thiểu <strong>12h làm việc</strong>; vào{" "}
                <strong>chiều Thứ 2</strong>, <strong>sáng Thứ 4</strong> và{" "}
                <strong>sáng Thứ 6</strong> hàng tuần. <br />
                <em>Địa điểm nhận:</em> Tầng 1 nhà T1.
              </Paragraph>
            </>
          }
        />

        <Card title="Danh sách giấy xác nhận đã đăng ký" bordered>
          <Table columns={columns} dataSource={dsYeuCau} />
        </Card>
      </Space>
    </>
  );
};
