import 'antd/dist/antd.css';
import { createContext, useEffect, useState } from 'react';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './App.css';
import Result from './components/Result';
import { Routes, Route, BrowserRouter, Link } from 'react-router-dom';
import System from './pages/System';
import Home from './pages/home/index';
export const ModalContext = createContext();
function App() {
  const [visible, setVisible] = useState(false);
  const [content, setContent] = useState('');
  useEffect(() => {
  }, []);
  return (
    <BrowserRouter>
      <ModalContext.Provider
        value={{ visible, setVisible, setContent }}
      >
        <div className='app'>
          <nav className="navbar navbar-expand-lg navbar-light sticky-top py-3" style={{ backgroundColor: '#6B6361' }} id="mainNav">
            <div className="container px-4 px-lg-5">
              <Link className="navbar-brand" to="/">Hệ Thống Đánh Giá Chỉ Số Sức Khỏe</Link>
              <button className="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                aria-label="Toggle navigation"><span className="navbar-toggler-icon"></span></button>
              <div className="collapse navbar-collapse" id="navbarResponsive">
                <ul className="navbar-nav ms-auto my-2 my-lg-0">
                  <li className="nav-item"><Link className="nav-link" to="/">Giới thiệu</Link></li>
                  <li className="nav-item"><Link className="nav-link" to="/system">Hệ thống</Link></li>
                </ul>
              </div>
            </div>
          </nav>

          <Routes>
            <Route path='/' element={<Home />}></Route>
            <Route path='/system' element={<System/>}></Route>
          </Routes>
        </div>
        <Result content={content} setContent={setContent}></Result>
        <ToastContainer draggable closeOnClick position='top-right' />
      </ModalContext.Provider>
    </BrowserRouter>
  );
}

export default App;
