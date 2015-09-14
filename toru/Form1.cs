using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SQLite;
using System.IO;

namespace toru
{
    public partial class Form1 : Form
    {

        SQLiteConnection m_dbConnection;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                SQLiteConnection.CreateFile("toru.sqlite");
                SQLiteConnection m_dbConnection;
                m_dbConnection = new SQLiteConnection("Data Source=toru.sqlite;Version=3;");
                m_dbConnection.Open();

                try
                {
                    string sql = @"
                            CREATE TABLE `sections` (
	                            `section_id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	                            `ojs_id`	INTEGER,
	                            `name`	TEXT
                            );
                            CREATE TABLE `issues` (
	                            `id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	                            `title`	TEXT,
	                            `volume`	INTEGER,
	                            `number`	INTEGER,
	                            `year`	TEXT,
	                            `show_title`	INTEGER,
	                            `show_volume`	INTEGER,
	                            `show_number`	INTEGER,
	                            `show_year`	INTEGER,
	                            `date_published` DATETIME,
	                            `synced_to_ojs`	INTEGER
                            );
                            CREATE TABLE `articles` (
	                            `id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	                            `title`	TEXT,
	                            `issue_id`	INTEGER,
	                            `abstract`	TEXT,
	                            `section_id`	INTEGER,
	                            `doi`	TEXT,
	                            `pubid`	TEXT,
	                            FOREIGN KEY(`id`) REFERENCES sections.id,
	                            FOREIGN KEY(`issue_id`) REFERENCES issues.id
                            );
                            ";
                    SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
                    command.ExecuteNonQuery();
                }
                catch (System.Data.SQLite.SQLiteException)
                {
                    //table already exists
                }
            }
            catch (IOException)
            {
                m_dbConnection = new SQLiteConnection("Data Source=toru.sqlite;Version=3;");
                m_dbConnection.Open();
            }

            // fill the issues grid!
            this.issue_grid_source();

        }

        private void issue_grid_source()
        {
            string sql = "SELECT * FROM issues";
            SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
            SQLiteDataAdapter da = new SQLiteDataAdapter(command);

            DataSet ds = new DataSet();

            try
            {
                da.Fill(ds);
                DataTable dt = ds.Tables[0];
                dataGridView1.DataSource = dt;
            }

            catch (Exception ex)
            {
                // Nothing
            }
        }

        private void create_database_Click(object sender, EventArgs e)
        {
            this.issue_grid_source();
        }

        private void add_issue_Click(object sender, EventArgs e)
        {
            string sql = @"INSERT INTO issues (title, volume, number, year, show_title, show_volume, show_number, show_year, date_published, synced_to_ojs) VALUES (@title, @volume, @number, @year, @show_title, @show_volume, @show_issue, @show_year, @date_published, 0);";

            int chk_title = 0;
            int chk_volume = 0;
            int chk_issue = 0;
            int chk_year = 0;

            if (check_title.Checked) { chk_title = 1; }
            if (check_volume.Checked) { chk_volume = 1; }
            if (check_issue.Checked) { chk_issue = 1; }
            if (check_year.Checked) { chk_year = 1; }

            SQLiteCommand command = new SQLiteCommand(sql, m_dbConnection);
            command.Parameters.Add(new SQLiteParameter("@title") { Value = title.Text });
            command.Parameters.Add(new SQLiteParameter("@volume") { Value = volume.Text });
            command.Parameters.Add(new SQLiteParameter("@number") { Value = number.Text });
            command.Parameters.Add(new SQLiteParameter("@year") { Value = year.Text });
            command.Parameters.Add(new SQLiteParameter("@show_title") { Value = chk_title });
            command.Parameters.Add(new SQLiteParameter("@show_volume") { Value = chk_volume });
            command.Parameters.Add(new SQLiteParameter("@show_issue") { Value = chk_issue });
            command.Parameters.Add(new SQLiteParameter("@show_year") { Value = chk_year });
            command.Parameters.Add(new SQLiteParameter("@date_published") { Value = date_published.Value });
            command.ExecuteNonQuery();

            this.issue_grid_source();
            reset_all_controls(this);
        }

        public static void reset_all_controls(Control form)
        {
            foreach (Control control in form.Controls)
            {
                if (control is TextBox)
                {
                    TextBox textBox = (TextBox)control;
                    textBox.Text = null;
                }

                if (control is ComboBox)
                {
                    ComboBox comboBox = (ComboBox)control;
                    if (comboBox.Items.Count > 0)
                        comboBox.SelectedIndex = 0;
                }

                if (control is CheckBox)
                {
                    CheckBox checkBox = (CheckBox)control;
                    checkBox.Checked = false;
                }

                if (control is ListBox)
                {
                    ListBox listBox = (ListBox)control;
                    listBox.ClearSelected();
                }
            }
        }
    }
}
