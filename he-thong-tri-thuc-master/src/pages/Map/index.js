import React from 'react';

const Map = () => {
  return (
    <div style={{ height: 'calc(100vh - 60px)' }}>
      <iframe
        src='https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3725.30155394837!2d105.78570991531333!3d20.980545894797544!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135acce762c2bb9%3A0xbb64e14683ccd786!2zSOG7jWMgVmnhu4duIENOIELGsHUgQ2jDrW5oIFZp4buFbiBUaMO0bmcgLSBIw6AgxJDDtG5n!5e0!3m2!1svi!2s!4v1641741078382!5m2!1svi!2s'
        width='100%'
        height='100%'
        style={{ border: 0 }}
        allowfullscreen=''
        loading='lazy'
      ></iframe>
    </div>
  );
};

export default Map;
