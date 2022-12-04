import React from 'react';
import { Link } from 'react-router-dom';
import image from '../../assets/image/anh1.png'

const Home = () => {
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
      {/* <section class="page-section" id="services">
        <div class="container px-4 px-lg-5">
          <h2 class="text-center mt-0">Tri thức của hệ thống</h2>
          <hr class="divider" />
          <div class="row gx-4 gx-lg-5">
            <img style={{ height: 800, }} src={image} />
            <p class="mt-4">Hệ thống giải quyết theo 2 phương diện:</p>
            <p class="mt-2 ml-2">- Bộ điều khiển đánh lái mô phỏng các hành vi sau của con người:</p>
            <p class="ml-4">+ lái chiếc xe về phía mục tiêu (đi thẳng hoặc rẽ trái, phải).</p>
            <p class="ml-4">+ lái xe quanh bất kỳ chướng ngại vật nào để tránh va chạm.</p>
            <p class="ml-4">+ điều khiển xe dừng lại ở một hướng mong muốn.</p>
            <p class="mt-2 ml-2">- Bộ điều khiển vận tốc mô phỏng các hành vi sau của con người:</p>
            <p class="ml-4">+ khởi động xe từ điểm dừng hoàn toàn và dừng xe khi xe đến mục tiêu.</p>
            <p class="ml-4">+ giảm tốc độ xe khi nó đến gần chướng ngại vật và tăng tốc khi nó vượt qua chướng ngại
              vật.</p>
            <p class="ml-4">+ giảm tốc độ xe khi đi vào khúc cua, đoạn cần rẽ.</p>
          </div>
        </div>
      </section> */}
    </div>
  );
};

export default Home;
