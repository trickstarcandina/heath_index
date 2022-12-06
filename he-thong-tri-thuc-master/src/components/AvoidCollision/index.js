import { Button, Col, Form, InputNumber, Row, Table } from 'antd';
import React, { useContext, useState } from 'react';
import { toast } from 'react-toastify';
import { ModalContext } from '../../App';
import { avoidCollision } from '../../controllers/avoidCollision';
// import { ModalContext } from '../../App';
export default function AvoidCollision() {
    const [distance, setDistance] = useState(20);
    const [speed, setSpeed] = useState(10);
    const [form] = Form.useForm();
    const { setContent } = useContext(ModalContext);

    const onSubmit = (data) => {
        const result = avoidCollision(data);
        setContent(
            `${result.resultWheel === 0 ? "Giữ nguyên góc bánh xe" : `Cần xoay góc bánh xe sang bên ${result.resultWheel > 0 ? 'phải' : 'trái'
                } một góc: ${Math.abs(result.resultWheel).toFixed(
                    2,
                )} độ`}, và ${result.resultPedal > 0
                    ? 'ấn ga thêm một góc: ' +
                    Math.abs(result.resultPedal).toFixed(2) +
                    ' độ'
                    : result.resultPedal === 0
                        ? 'giữ nguyên chân ga'
                        : 'nhả ga ấn phanh thêm một góc: ' +
                        Math.abs(result.resultPedal).toFixed(2) +
                        ' độ'
            }`,
        );
    };

    const generateData = () => {
        const columns = [
            {
                title: 'Khoảng cách',
                dataIndex: 'khoang_cach',
                key: 'khoang_cach',
            },
            {
                title: 'Vận tốc hiện tại',
                dataIndex: 'van_toc_hien_tai',
                key: 'van_toc_hien_tai',
            },
            {
                title: 'Góc lái',
                dataIndex: 'goc_lai',
                key: 'goc_lai',
            },
            {
                title: 'Góc tới mục tiêu',
                dataIndex: 'goc_muc_tieu',
                key: 'goc_muc_tieu',
            },
            
            {
                title: 'Hướng chướng ngại vật',
                dataIndex: 'huong_chuong_ngai_vat',
                key: 'huong_chuong_ngai_vat',
            },
            {
                title: 'Vận tốc tương đối',
                dataIndex: 'van_toc_tuong_doi',
                key: 'van_toc_tuong_doi',
            },
            {
                title: 'Kết luận',
                dataIndex: 'result',
                key: 'result',
                render: function (result) {
                    return `${result.resultWheel === 0 ? "Giữ nguyên góc bánh xe" : `Cần xoay góc bánh xe sang bên ${result.resultWheel > 0 ? 'phải' : 'trái'
                        } một góc: ${Math.abs(result.resultWheel).toFixed(
                            2,
                        )} độ`}, và ${result.resultPedal > 0
                            ? 'ấn ga thêm một góc: ' +
                            Math.abs(result.resultPedal).toFixed(2) +
                            ' độ'
                            : result.resultPedal === 0
                                ? 'giữ nguyên chân ga'
                                : 'nhả ga ấn phanh thêm một góc: ' +
                                Math.abs(result.resultPedal).toFixed(2) +
                                ' độ'
                        }`;
                },
            },
        ];
        let dataTable = [];
        for (let i = 0; i < 10; i++) {
            const khoang_cach = Math.round(Math.random() * 80 * 100) / 100;
            const van_toc_hien_tai = Math.round(Math.random() * 25 * 100) / 100;
            const data = {
                khoang_cach: khoang_cach,
                van_toc_hien_tai: van_toc_hien_tai,
                goc_lai: Math.round((Math.random() * 100 - 50) * 100) / 100,
                goc_muc_tieu: khoang_cach / van_toc_hien_tai > 5 && van_toc_hien_tai < 15 ? Math.round((Math.random() * 240 - 120) * 100) / 100 : 0,
                van_toc_tuong_doi: Math.round((Math.random() * 5 - 2.5) * 100) / 100,
                huong_chuong_ngai_vat: !(khoang_cach / van_toc_hien_tai > 5 && van_toc_hien_tai < 15) ? Math.round((Math.random() * 360 - 180) * 100) / 100 : 0,
            };
            const result = avoidCollision(data);
            data.result = result;
            dataTable.push(data)
        }
        const table = (
            <Table
                className='mt-2'
                columns={columns}
                dataSource={dataTable}
                pagination={false}
            />
        );
        setContent(table);
    };

    return (
        <Form
            form={form}
            layout='vertical'
            onFinish={onSubmit}
            onFinishFailed={() => {
                toast.error('Vui lòng nhập đúng yêu cầu');
            }}
            initialValues={{
                khoang_cach: 20,
                goc_lai: 0,
                goc_muc_tieu: 0,
                van_toc_hien_tai: 10,
                van_toc_tuong_doi: 0,
                huong_chuong_ngai_vat: 0,
            }}
        >
            <Row gutter={[10, 10]}>
                <Col span={8}>
                    <Form.Item
                        label='Khoảng cách đến chướng ngại vật (m)'
                        rules={[
                            {
                                required: true,
                                message: 'Khoảng cách đến chướng ngại vật là số',
                            },
                            {
                                type: 'number',
                                min: 0,
                                max: 80,
                                message:
                                    'Khoảng cách đến chướng ngại vật là số từ 0 đến 80',
                            },
                        ]}
                        name='khoang_cach'
                    >
                        <InputNumber
                            placeholder='từ 0 đến 80 độ'
                            style={{ width: '100%' }}
                            onChange={(value) => setDistance(value)}
                        />
                    </Form.Item>
                </Col>

                <Col span={8}>
                    <Form.Item
                        label='Góc lái hiện tại (độ)'
                        rules={[
                            { required: true, message: 'Góc lái phải là số' },
                            {
                                type: 'number',
                                min: -50,
                                max: 50,
                                message: 'Góc lái là số từ -50 đến 50',
                            },
                        ]}
                        name='goc_lai'
                    >
                        <InputNumber
                            placeholder='từ -50 đến 50 độ'
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                <Col span={8}>
                    <Form.Item
                        label='Vận tốc hiện tại (m/s)'
                        rules={[
                            {
                                required: true,
                                message: 'Vận tốc hiện tại phải là số',
                            },
                            {
                                type: 'number',
                                min: 0,
                                max: 25,
                                message: 'Vận tốc hiện tại là số từ 0 đến 25',
                            },
                        ]}
                        name='van_toc_hien_tai'
                    >
                        <InputNumber
                            placeholder='từ 0 đến 25'
                            style={{ width: '100%' }}
                            onChange={setSpeed}
                        />
                    </Form.Item>
                </Col>
                <Col span={8}>
                    <Form.Item
                        label='Vận tốc tương đối (m/s)'
                        rules={[
                            {
                                required: true,
                                message: 'Vận tốc tương đối phải là số',
                            },
                            {
                                type: 'number',
                                min: -5,
                                max: 5,
                                message: 'Vận tốc tương đối là số từ -5 đến 5',
                            },
                        ]}
                        name='van_toc_tuong_doi'
                    >
                        <InputNumber
                            placeholder='từ -5 đến 5'
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                {distance / speed > 5 && speed < 15 ? (
                    <Col span={8}>
                        <Form.Item
                            label='Góc đến mục tiêu (độ)'
                            rules={[
                                {
                                    required: true,
                                    message: 'Góc đến mục tiêu phải là số',
                                },
                                {
                                    type: 'number',
                                    min: -120,
                                    max: 120,
                                    message: 'Góc đến mục tiêu là số từ -120 đến 120',
                                },
                            ]}
                            name='goc_muc_tieu'
                        >
                            <InputNumber
                                placeholder='từ -120 đến 120'
                                style={{ width: '100%' }}
                            />
                        </Form.Item>
                    </Col>
                ) : (
                    <Col span={8}>
                        <Form.Item
                            label='Hướng của chướng ngại vật (độ)'
                            rules={[
                                {
                                    required: true,
                                    message: 'Hướng của chướng ngại vật phải là số',
                                },
                                {
                                    type: 'number',
                                    min: -180,
                                    max: 180,
                                    message:
                                        'Hướng của chướng ngại vật là số từ -180 đến 180',
                                },
                            ]}
                            name='huong_chuong_ngai_vat'
                        >
                            <InputNumber
                                placeholder='từ -180 đến 180'
                                style={{ width: '100%' }}
                            />
                        </Form.Item>
                    </Col>
                )}
            </Row>
            <Form.Item>
                <Button type='primary' htmlType='submit'>
                    Submit
                </Button>
                <Button className='m-3' type='primary' onClick={generateData}>
                    GenerateData
                </Button>
            </Form.Item>
        </Form>
    );
}
