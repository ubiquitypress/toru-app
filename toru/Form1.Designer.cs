namespace toru
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
            this.create_database = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.label2 = new System.Windows.Forms.Label();
            this.volume = new System.Windows.Forms.TextBox();
            this.number = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.title = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.year = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.date_published = new System.Windows.Forms.DateTimePicker();
            this.label12 = new System.Windows.Forms.Label();
            this.add_issue = new System.Windows.Forms.Button();
            this.check_title = new System.Windows.Forms.CheckBox();
            this.check_issue = new System.Windows.Forms.CheckBox();
            this.check_volume = new System.Windows.Forms.CheckBox();
            this.check_year = new System.Windows.Forms.CheckBox();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // create_database
            // 
            this.create_database.Location = new System.Drawing.Point(343, 33);
            this.create_database.Name = "create_database";
            this.create_database.Size = new System.Drawing.Size(126, 23);
            this.create_database.TabIndex = 0;
            this.create_database.Text = "Refresh Issues";
            this.create_database.UseVisualStyleBackColor = true;
            this.create_database.Click += new System.EventHandler(this.create_database_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(25, 63);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(0, 13);
            this.label1.TabIndex = 1;
            // 
            // dataGridView1
            // 
            this.dataGridView1.AllowUserToAddRows = false;
            this.dataGridView1.AllowUserToDeleteRows = false;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(343, 71);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(1208, 381);
            this.dataGridView1.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(28, 94);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(42, 13);
            this.label2.TabIndex = 3;
            this.label2.Text = "Volume";
            // 
            // volume
            // 
            this.volume.Location = new System.Drawing.Point(28, 110);
            this.volume.Name = "volume";
            this.volume.Size = new System.Drawing.Size(261, 20);
            this.volume.TabIndex = 4;
            // 
            // number
            // 
            this.number.Location = new System.Drawing.Point(28, 152);
            this.number.Name = "number";
            this.number.Size = new System.Drawing.Size(261, 20);
            this.number.TabIndex = 7;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(28, 133);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(44, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "Number";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(25, 121);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(0, 13);
            this.label4.TabIndex = 5;
            // 
            // title
            // 
            this.title.Location = new System.Drawing.Point(28, 71);
            this.title.Name = "title";
            this.title.Size = new System.Drawing.Size(261, 20);
            this.title.TabIndex = 10;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(28, 55);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(27, 13);
            this.label5.TabIndex = 9;
            this.label5.Text = "Title";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(25, 173);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(0, 13);
            this.label6.TabIndex = 8;
            // 
            // year
            // 
            this.year.Location = new System.Drawing.Point(28, 191);
            this.year.Name = "year";
            this.year.Size = new System.Drawing.Size(261, 20);
            this.year.TabIndex = 13;
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(25, 241);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(0, 13);
            this.label8.TabIndex = 11;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(28, 175);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(29, 13);
            this.label7.TabIndex = 12;
            this.label7.Text = "Year";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(25, 214);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(80, 13);
            this.label11.TabIndex = 17;
            this.label11.Text = "Display Options";
            // 
            // date_published
            // 
            this.date_published.Location = new System.Drawing.Point(28, 313);
            this.date_published.Name = "date_published";
            this.date_published.Size = new System.Drawing.Size(261, 20);
            this.date_published.TabIndex = 18;
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(25, 297);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(107, 13);
            this.label12.TabIndex = 19;
            this.label12.Text = "Date/Time Published";
            // 
            // add_issue
            // 
            this.add_issue.Location = new System.Drawing.Point(28, 350);
            this.add_issue.Name = "add_issue";
            this.add_issue.Size = new System.Drawing.Size(261, 23);
            this.add_issue.TabIndex = 20;
            this.add_issue.Text = "Add New Issue";
            this.add_issue.UseVisualStyleBackColor = true;
            this.add_issue.Click += new System.EventHandler(this.add_issue_Click);
            // 
            // check_title
            // 
            this.check_title.AutoSize = true;
            this.check_title.Location = new System.Drawing.Point(28, 230);
            this.check_title.Name = "check_title";
            this.check_title.Size = new System.Drawing.Size(76, 17);
            this.check_title.TabIndex = 21;
            this.check_title.Text = "Show Title";
            this.check_title.UseVisualStyleBackColor = true;
            // 
            // check_issue
            // 
            this.check_issue.AutoSize = true;
            this.check_issue.Location = new System.Drawing.Point(28, 253);
            this.check_issue.Name = "check_issue";
            this.check_issue.Size = new System.Drawing.Size(81, 17);
            this.check_issue.TabIndex = 22;
            this.check_issue.Text = "Show Issue";
            this.check_issue.UseVisualStyleBackColor = true;
            // 
            // check_volume
            // 
            this.check_volume.AutoSize = true;
            this.check_volume.Location = new System.Drawing.Point(154, 230);
            this.check_volume.Name = "check_volume";
            this.check_volume.Size = new System.Drawing.Size(91, 17);
            this.check_volume.TabIndex = 23;
            this.check_volume.Text = "Show Volume";
            this.check_volume.UseVisualStyleBackColor = true;
            // 
            // check_year
            // 
            this.check_year.AutoSize = true;
            this.check_year.Location = new System.Drawing.Point(154, 253);
            this.check_year.Name = "check_year";
            this.check_year.Size = new System.Drawing.Size(78, 17);
            this.check_year.TabIndex = 24;
            this.check_year.Text = "Show Year";
            this.check_year.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1606, 716);
            this.Controls.Add(this.check_year);
            this.Controls.Add(this.check_volume);
            this.Controls.Add(this.check_issue);
            this.Controls.Add(this.check_title);
            this.Controls.Add(this.add_issue);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.date_published);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.year);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.title);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.number);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.volume);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.create_database);
            this.Name = "Form1";
            this.Text = "Toru - Offline JMS";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button create_database;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox volume;
        private System.Windows.Forms.TextBox number;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox title;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox year;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.DateTimePicker date_published;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Button add_new_issue;
        private System.Windows.Forms.Button add_issue;
        private System.Windows.Forms.CheckBox check_title;
        private System.Windows.Forms.CheckBox check_issue;
        private System.Windows.Forms.CheckBox check_volume;
        private System.Windows.Forms.CheckBox check_year;
    }
}

