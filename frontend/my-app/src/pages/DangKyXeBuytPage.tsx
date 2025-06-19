import {
  Alert,
  Button,
  Card,
  Col,
  Form,
  Row,
  Select,
  Space,
  Table,
  Typography,
  Radio,
  Flex,
  Input,
  Upload,
} from "antd";
import axios from "axios";
import { useEffect, useState } from "react";
import { TuyenXeModel } from "../models/TuyenXeModel";
import { PlusOutlined } from "@ant-design/icons";
const { Option } = Select;
const { Paragraph } = Typography;
export const DangKyXeBuytPage = () => {
  const [selectTuyen, setSelectTuyen] = useState("Mot tuyen");
  const [tuyenXe, setTuyenXe] = useState<TuyenXeModel[]>([]);
  const [form] = Form.useForm();

  const handleSelectTuyen = (e: any) => {
    const value = e.target.value;
    setSelectTuyen(value === "motTuyen" ? "Mot tuyen" : "Lien tuyen");
  };
  const normFile = (e: any) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e?.fileList;
  };
  useEffect(() => {
    const fetchTuyenXe = async () => {
      const url = `http://localhost:8080/api/secure/tuyenxe/get-all`;
      const response = await axios.get(url);

      const listTuyenXe = response.data.map((item: any) => {
        return {
          id: item.id,
          ten: item.ten,
          maTuyen: item.maTuyen,
        };
      });
      console.log(listTuyenXe);
      setTuyenXe(listTuyenXe);
    };
    fetchTuyenXe();
  }, []);
  const handleSubmit = () => {
    console.log(form.getFieldsValue());
  };
  return (
    <>
      <Space direction="vertical" size="large" style={{ width: "100%" }}>
        <Form form={form} onFinish={handleSubmit} style={{ maxWidth: "100%" }}>
          <Card title="Chọn loại giấy xác nhận" bordered>
            <Row gutter={[16, 16]}>
              <Col span={24}>
                <Form.Item name="tuyen">
                  <Radio.Group
                    onChange={handleSelectTuyen}
                    defaultValue={1}
                    options={[
                      {
                        value: "motTuyen",
                        className: "option-1",
                        label: (
                          <Flex
                            gap="small"
                            justify="center"
                            align="center"
                            vertical
                          >
                            Một tuyến
                          </Flex>
                        ),
                      },
                      {
                        value: "lienTuyen",
                        className: "option-2",
                        label: (
                          <Flex
                            gap="small"
                            justify="center"
                            align="center"
                            vertical
                          >
                            Liên tuyến
                          </Flex>
                        ),
                      },
                    ]}
                    optionType="button"
                    buttonStyle="solid"
                  />
                </Form.Item>
              </Col>
              {selectTuyen === "Mot tuyen" ? (
                <Col span={24}>
                  <Form.Item name="selectedTuyen">
                    <Select
                      placeholder="Chọn tuyến"
                      style={{
                        width: "100%",
                      }}
                    >
                      {tuyenXe.map((item: any) => (
                        <Option key={item.id} value={item.ten}>
                          <div
                            style={{
                              whiteSpace: "normal",
                              wordBreak: "break-word",
                            }}
                          >
                            {item.ten} [{item.maTuyen}]
                          </div>
                        </Option>
                      ))}
                    </Select>
                  </Form.Item>
                </Col>
              ) : (
                <div></div>
              )}
              <Col span={24}>
                <Form.Item
                  name="sdt"
                  rules={[{ required: true, message: "Bắt buộc nhập" }]}
                >
                  <Input placeholder="Số điện thoại"></Input>
                </Form.Item>
              </Col>
              <Col span={24}>
                <Form.Item
                  label="Upload"
                  valuePropName="fileList"
                  getValueFromEvent={normFile}
                  name="anh"
                >
                  <Upload listType="picture-card" beforeUpload={() => false}>
                    <button
                      style={{
                        color: "inherit",
                        cursor: "inherit",
                        border: 0,
                        background: "none",
                      }}
                      type="button"
                    >
                      <PlusOutlined />
                      <div style={{ marginTop: 8 }}>Ảnh</div>
                    </button>
                  </Upload>
                </Form.Item>
              </Col>
              <Button
                type="primary"
                htmlType="submit"
                // disabled={!selectedGiay}
              >
                Đăng ký
              </Button>
            </Row>
          </Card>
        </Form>
        <Alert
          type="info"
          showIcon
          message="Quy định về việc trả kết quả giấy tờ"
          description={
            <>
              <Paragraph>
                <strong>• Ngày hẹn trả kết quả:</strong>
                <br />
                Kết quả sẽ được trả sau tối thiểu{" "}
                <strong>12 giờ làm việc</strong>; vào{" "}
                <strong>chiều Thứ 2</strong>, <strong>sáng Thứ 4</strong> và{" "}
                <strong>sáng Thứ 6</strong> hàng tuần.
              </Paragraph>

              <Paragraph>
                <strong>• Địa điểm nhận:</strong> Tầng 1, nhà T1.
              </Paragraph>

              <Paragraph>
                <strong>• Khi đến nhận kết quả:</strong> Sinh viên cần nộp{" "}
                <strong>02 ảnh thẻ 3x4</strong> (đã rửa).
              </Paragraph>

              <Paragraph>
                <strong>• Thời gian lưu kết quả:</strong> Kết quả sẽ được lưu
                giữ tại phòng trong vòng <strong>02 tuần</strong> kể từ ngày hẹn
                trả kết quả. Sau thời gian này, nếu sinh viên chưa đến nhận, vui
                lòng <strong>đăng ký lại</strong> để được cấp lại giấy.
              </Paragraph>
            </>
          }
        />

        <Card title="Danh sách giấy xác nhận đã đăng ký">
          <Table
            // columns={columns}
            // dataSource={dsYeuCau}
            scroll={{ x: "max-content" }}
          />
        </Card>
      </Space>
    </>
  );
};
