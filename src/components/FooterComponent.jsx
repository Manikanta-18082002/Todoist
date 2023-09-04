// eslint-disable-next-line no-unused-vars
import React from 'react'

const FooterComponent = () => {

    const currentYear = new Date().getFullYear();

  return (
    <div>
        <footer className='footer'>
            <p className='text-center'>Copyrights reserved at {currentYear} by ManiKanta</p>
        </footer>
    </div>
  )
}

export default FooterComponent