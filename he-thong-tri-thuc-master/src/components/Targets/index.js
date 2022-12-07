import { Button, Col, Form, InputNumber, Row, Select } from 'antd';
import React, { useContext } from 'react';
import { toast } from 'react-toastify';
import { ModalContext } from '../../App';
export default function Target() {
    const [form] = Form.useForm();
    const { setContent } = useContext(ModalContext);

    const onSubmit = (datas) => {


        const data = {
            "age": datas.old,
            "gender": datas.gender,
            "height": datas.height,
            "weight": datas.weight,
            "blood_sugar": datas.sugar,
            "heart_beat": datas.heartbeat,
            "cholesterol": datas.cholesterol
        }
        fetch('https://heath-index.up.railway.app/api/sendData', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(function(res){
                return res.json();
            })
            .then(function(response) {
                const b = response.result;
                setContent(b)
            })
            .catch(function(err) {
                console.log("err: ", err);
                toast.error(err);
            })
        
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
                old: 10,
                heartbeat: 100,
                cholesterol: 185,
                sugar: 8.5,
                height: 27,
                weight: 50,
                gender: 'nam'
            }}
        >
            <Row gutter={[10, 10]}>
                <Col span={8}>
                    <Form.Item
                        label='Giới tính'
                        name='gender'
                    >
                    <Select
                        defaultValue="Nam"
                        options={[
                            {
                                value: 'nu',
                                label: 'Nữ',
                            },
                            {
                                value: 'nam',
                                label: 'Nam',
                            },
                        ]}
                    />
                    </Form.Item>
                </Col>
                <Col span={8}>
                    <Form.Item
                        label='Tuổi'
                        rules={[
                            {
                                required: true,
                                message: 'Tuổi phải là số',
                            },
                            {
                                type: 'number',
                                min: 1,
                                max: 50,
                                message:
                                    'Tuổi phải từ 1-50',
                            },
                        ]}
                        name='old'
                    >
                        <InputNumber
                            placeholder='từ 1 đến 50'
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                <Col span={8}>
                </Col>

                <Col span={8}>
                    <Form.Item
                        label='Chỉ số nhịp tim (lần/phút)'
                        rules={[
                            { required: true, message: 'Chỉ số nhịp tim phải là số' },
                            {
                                type: 'number',
                                min: 30,
                                max: 200,
                                message: 'Chỉ số nhịp tim phải là số từ 30 đến 200',
                            },
                        ]}
                        name='heartbeat'
                    >
                        <InputNumber
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                <Col span={8}>
                    <Form.Item
                        label='Chỉ số cholesterol (mg/dL)'
                        rules={[
                            {
                                required: true,
                                message: 'Chỉ số cholesterol phải là số',
                            },
                            {
                                type: 'number',
                                min: 50,
                                max: 250,
                                message: 'Chỉ số cholesterol là số từ 50 đến 250',
                            },
                        ]}
                        name='cholesterol'
                    >
                        <InputNumber
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                <Col span={8}>
                    <Form.Item
                        label='Chỉ số đường huyết (mg/dL)'
                        rules={[
                            {
                                required: true,
                                message: 'Chỉ số đường huyết phải là số',
                            },
                            {
                                type: 'number',
                                min: 0,
                                max: 20,
                                message: 'Chỉ số đường huyết là số lớn hơn 0 và nhỏ hơn 20',
                            },
                        ]}
                        name='sugar'
                    >
                        <InputNumber
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                <Col span={8}>
                    <Form.Item
                        label='Chiều cao(cm)'
                        rules={[
                            {
                                required: true,
                                message: 'Chiều cao phải là số',
                            },
                            {
                                type: 'number',
                                min: 0,
                                max: 300,
                                message: 'Chiều cao là số lớn hơn 0 và nhỏ hơn 300',
                            },
                        ]}
                        name='height'
                    >
                        <InputNumber
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                <Col span={8}>
                    <Form.Item
                        label='Cân nặng (kg)'
                        rules={[
                            {
                                required: true,
                                message: 'Cân nặng tiêu phải là số',
                            },
                            {
                                type: 'number',
                                min: 0,
                                message:
                                    'Cân nặng phải là số lớn hơn không',
                            },
                        ]}
                        name='weight'
                    >
                        <InputNumber
                            style={{ width: '100%' }}
                        />
                    </Form.Item>
                </Col>
                
            </Row>
            <Form.Item>
                <Button type='primary' htmlType='submit'>
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
}
