import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import image from '../../assets/image/anh1.png'
import { Modal, Button } from 'antd';

const Home = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    setIsModalOpen(false);
  };

  const handleCancel = () => {
    setIsModalOpen(false);
  };
  return (
    <div>
      <header className="masthead">
        <div className="container px-4 px-lg-5 h-100">
          <div className="row gx-4 gx-lg-5 h-100 align-items-center justify-content-center text-center">
            <div className="col-lg-8 align-self-end">
              <h1 className="text-white font-weight-bold">Hệ thống đánh giá chỉ số sức khỏe sử dụng kết hợp mạng nơ ron nhân tạo và hệ mờ</h1>
              <hr className="divider" />
            </div>
            <div className="col-lg-8 align-self-baseline">
              <Link className="btn btn-primary btn-xl" to="/system">Bắt đầu</Link>
            </div>
          </div>
        </div>
      </header>
    </div>
  );
};

export default Home;
