import { Modal } from 'antd';
import React, { useContext } from 'react';
import { ModalContext } from '../../App';

const Result = ({ content, setContent }) => {
  console.log("content: 123", content);
  return (
    <>
      <Modal
        title='Kết quả'
        centered
        visible={content}
        onOk={() => setContent('')}
        onCancel={() => setContent('')}
        width={1000}
      >
        <div>Chỉ số sức khỏe: {content}</div>
        <div>(0->0.3: rất kém</div>
        <div>0.31->0.5 kém</div>
        <div>0.51->0.7 bình thường</div>
        <div>0.71->0.85 tốt</div>
        <div>0.86->1 rất tốt)</div>
      </Modal>
    </>
  );
};
export default Result;
