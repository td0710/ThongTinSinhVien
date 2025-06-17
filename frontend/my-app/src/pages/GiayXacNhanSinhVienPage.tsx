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
const { Paragraph } = Typography;
const { Option } = Select;

export const GiayXacNhanSinhVienPage = () => {
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
      title: "Ghi chú",
      dataIndex: "ghiChu",
      key: "ghiChu",
    },
  ];

  const dataSource = [
    {
      key: "1",
      loai: "Xác nhận sinh viên",
      ngayDangKy: "01/06/2025",
      trangThai: "Đã duyệt",
    },
    {
      key: "2",
      loai: "Miễn giảm học phí",
      ngayDangKy: "05/06/2025",
      trangThai: "Đang xử lý",
    },
  ];
  return (
    <>
      <Space direction="vertical" size="large" style={{ width: "100%" }}>
        <Form>
          <Card title="Chọn loại giấy xác nhận" bordered>
            <Row gutter={[16, 16]}>
              <Col span={12}>
                <Form.Item name="giayXacNhan">
                  <Select
                    placeholder="Chọn loại giấy xác nhận"
                    // onChange={handleChange}
                    style={{ width: "100%" }}
                  >
                    <Option value="xac_nhan_sv">Xác nhận sinh viên</Option>
                    <Option value="mien_giam_hp">Miễn giảm học phí</Option>
                    <Option value="xac_nhan_tam_tru">Xác nhận tạm trú</Option>
                  </Select>
                </Form.Item>
                <Button
                  type="primary"
                  htmlType="submit"
                  // onClick={handleSubmit}
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
          <Table columns={columns} dataSource={dataSource} />
        </Card>
      </Space>
    </>
  );
};
