namespace monitor
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.cbxPorta = new System.Windows.Forms.ComboBox();
            this.btnConectar = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.btnDesconectar = new System.Windows.Forms.Button();
            this.panel1 = new System.Windows.Forms.Panel();
            this.txtMensagem = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.txt001 = new System.Windows.Forms.TextBox();
            this.txtMsg001 = new System.Windows.Forms.TextBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // cbxPorta
            // 
            this.cbxPorta.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbxPorta.FormattingEnabled = true;
            this.cbxPorta.Items.AddRange(new object[] {
            "COM1",
            "COM2",
            "COM3",
            "COM4",
            "COM5"});
            this.cbxPorta.Location = new System.Drawing.Point(54, 10);
            this.cbxPorta.Name = "cbxPorta";
            this.cbxPorta.Size = new System.Drawing.Size(62, 21);
            this.cbxPorta.TabIndex = 0;
            // 
            // btnConectar
            // 
            this.btnConectar.Location = new System.Drawing.Point(122, 8);
            this.btnConectar.Name = "btnConectar";
            this.btnConectar.Size = new System.Drawing.Size(75, 23);
            this.btnConectar.TabIndex = 1;
            this.btnConectar.Text = "Conectar";
            this.btnConectar.UseVisualStyleBackColor = true;
            this.btnConectar.Click += new System.EventHandler(this.btnConectar_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(35, 13);
            this.label1.TabIndex = 2;
            this.label1.Text = "Porta:";
            // 
            // btnDesconectar
            // 
            this.btnDesconectar.Enabled = false;
            this.btnDesconectar.Location = new System.Drawing.Point(203, 8);
            this.btnDesconectar.Name = "btnDesconectar";
            this.btnDesconectar.Size = new System.Drawing.Size(90, 23);
            this.btnDesconectar.TabIndex = 3;
            this.btnDesconectar.Text = "Desconectar";
            this.btnDesconectar.UseVisualStyleBackColor = true;
            this.btnDesconectar.Click += new System.EventHandler(this.btnDesconectar_Click);
            // 
            // panel1
            // 
            this.panel1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.panel1.AutoScroll = true;
            this.panel1.Controls.Add(this.textBox2);
            this.panel1.Controls.Add(this.textBox1);
            this.panel1.Controls.Add(this.txtMsg001);
            this.panel1.Controls.Add(this.txt001);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.txtMensagem);
            this.panel1.Location = new System.Drawing.Point(-1, 37);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(452, 252);
            this.panel1.TabIndex = 4;
            // 
            // txtMensagem
            // 
            this.txtMensagem.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txtMensagem.Location = new System.Drawing.Point(4, 24);
            this.txtMensagem.Multiline = true;
            this.txtMensagem.Name = "txtMensagem";
            this.txtMensagem.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.txtMensagem.Size = new System.Drawing.Size(428, 72);
            this.txtMensagem.TabIndex = 0;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 4);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(62, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "Mensagem:";
            // 
            // txt001
            // 
            this.txt001.Location = new System.Drawing.Point(6, 103);
            this.txt001.Name = "txt001";
            this.txt001.Size = new System.Drawing.Size(44, 20);
            this.txt001.TabIndex = 6;
            this.txt001.Text = "001";
            // 
            // txtMsg001
            // 
            this.txtMsg001.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txtMsg001.Location = new System.Drawing.Point(6, 129);
            this.txtMsg001.Multiline = true;
            this.txtMsg001.Name = "txtMsg001";
            this.txtMsg001.Size = new System.Drawing.Size(426, 67);
            this.txtMsg001.TabIndex = 7;
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(6, 202);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(44, 20);
            this.textBox1.TabIndex = 8;
            this.textBox1.Text = "001";
            // 
            // textBox2
            // 
            this.textBox2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.textBox2.Location = new System.Drawing.Point(6, 228);
            this.textBox2.Multiline = true;
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(426, 67);
            this.textBox2.TabIndex = 9;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(451, 290);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.btnDesconectar);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnConectar);
            this.Controls.Add(this.cbxPorta);
            this.Name = "Form1";
            this.Text = "Monitor Drone";
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox cbxPorta;
        private System.Windows.Forms.Button btnConectar;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btnDesconectar;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TextBox txtMsg001;
        private System.Windows.Forms.TextBox txt001;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtMensagem;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.TextBox textBox1;
    }
}

