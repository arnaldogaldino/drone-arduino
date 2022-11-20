using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO.Ports;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace monitor
{
    public partial class Form1 : Form
    {
        private Thread threadMaster = null;
        private SerialPort port = null;

        public Form1()
        {
            InitializeComponent();
            cbxPorta.SelectedItem = "COM4";
        }

        private void btnConectar_Click(object sender, EventArgs e)
        {
            btnDesconectar.Enabled = true;
            btnConectar.Enabled = false;
            cbxPorta.Enabled = false;

            port = new SerialPort(cbxPorta.SelectedItem.ToString());
            port.BaudRate = 9600;

            try
            {
                port.Open();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
                btnDesconectar_Click(sender, e);
            }

            threadMaster = new Thread(Process);
            threadMaster.Start();
        }

        private void btnDesconectar_Click(object sender, EventArgs e)
        {
            btnConectar.Enabled = true;
            btnDesconectar.Enabled = false;
            cbxPorta.Enabled = true;

            if(port != null) port.Close();
            port = null;

            if(threadMaster != null) threadMaster.Abort();
            threadMaster = null;
        }

        private void Process()
        {


            Thread.Sleep(100);
            Process();
        }
    }
}
