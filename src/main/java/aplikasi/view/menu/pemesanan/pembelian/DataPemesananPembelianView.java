/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.view.menu.pemesanan.pembelian;

import aplikasi.config.KoneksiDB;
import aplikasi.config.ValueFormatterFactory;
import aplikasi.controller.TableViewController;
import aplikasi.entity.Pemasok;
import aplikasi.entity.PesananPembelian;
import aplikasi.entity.PesananPembelianDetail;
import aplikasi.repository.RepoPemasok;
import aplikasi.repository.RepoPesananPembelian;
import aplikasi.service.ServicePemasok;
import aplikasi.service.ServicePesananPembelian;
import aplikasi.view.MainMenuView;
import java.beans.PropertyVetoException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dimmaryanto
 */
public class DataPemesananPembelianView extends javax.swing.JInternalFrame {

    private final RepoPemasok repoPemasok;
    private final RepoPesananPembelian repoPesananaPembelian;
    private final MainMenuView menuController;
    private final PesananPembelian pesanBarang;
    private final List<PesananPembelianDetail> daftarBelajaan;
    private final TableViewController tableController;
    private List<Pemasok> daftarPemasok;

    public void tambahBarangBelanjaan(PesananPembelianDetail belanja) {
        this.daftarBelajaan.add(belanja);
    }

    public DataPemesananPembelianView(MainMenuView menuController) {
        this.menuController = menuController;
        this.pesanBarang = new PesananPembelian();
        this.daftarBelajaan = new ArrayList<>();
        this.daftarPemasok = new ArrayList<>();
        this.repoPemasok = new ServicePemasok(KoneksiDB.getDataSource());
        this.repoPesananaPembelian = new ServicePesananPembelian(KoneksiDB.getDataSource());
        initComponents();
        refreshDataPemasok();
        this.txtTanggal.setDate(new java.util.Date());
        this.tableController = new TableViewController(tableView);
        try {
            StringBuilder sb = new StringBuilder("PO").append("-");

            sb.append(ValueFormatterFactory.getLocalDateShort(LocalDate.now())).append("-");

            List<PesananPembelian> daftarPO = repoPesananaPembelian.findAll();
            sb.append(String.format("%02d", daftarPO.size() + 1));
            txtKode.setText(sb.toString());
            this.pesanBarang.setKode(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(DataPemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refreshDataTable() {
        tableController.clearData();
        for (PesananPembelianDetail belanja : daftarBelajaan) {
            Object[] row = {belanja.getBarang().getKode(), belanja.getBarang().getNama(), belanja.getJumlah()};
            tableController.getModel().addRow(row);
        }
    }

    private void refreshDataPemasok() {
        try {
            txtKodePemasok.removeAllItems();
            daftarPemasok = repoPemasok.findAll();
            for (Pemasok pemasok : daftarPemasok) {
                txtKodePemasok.addItem(pemasok.getId().toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataPemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setFieldPemasok(Pemasok p) {
        txtNamaPemasok.setText(p.getNama());
        txtTelpPemasok.setText(p.getTlp());
    }

    private void clearFieldPemasok() {
        txtNamaPemasok.setText("");
        txtTelpPemasok.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnCetak = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnSimpan = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtKodePemasok = new javax.swing.JComboBox<>();
        txtNamaPemasok = new javax.swing.JTextField();
        txtTelpPemasok = new javax.swing.JTextField();
        txtKode = new javax.swing.JTextField();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableView = new javax.swing.JTable();
        btnTambahBarang = new javax.swing.JButton();
        btnHapusBarang = new javax.swing.JButton();

        setTitle("Data Daftar Belanjaan");

        jToolBar1.setRollover(true);

        btnCetak.setText("Cetak");
        btnCetak.setFocusable(false);
        btnCetak.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCetak.setMaximumSize(new java.awt.Dimension(120, 35));
        btnCetak.setMinimumSize(new java.awt.Dimension(120, 35));
        btnCetak.setPreferredSize(new java.awt.Dimension(120, 35));
        btnCetak.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCetak);
        jToolBar1.add(jSeparator1);

        btnSimpan.setText("Simpan");
        btnSimpan.setFocusable(false);
        btnSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSimpan.setMaximumSize(new java.awt.Dimension(120, 35));
        btnSimpan.setMinimumSize(new java.awt.Dimension(120, 35));
        btnSimpan.setPreferredSize(new java.awt.Dimension(120, 35));
        btnSimpan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSimpan);

        btnKembali.setText("Kembali");
        btnKembali.setFocusable(false);
        btnKembali.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKembali.setMaximumSize(new java.awt.Dimension(120, 35));
        btnKembali.setMinimumSize(new java.awt.Dimension(120, 35));
        btnKembali.setPreferredSize(new java.awt.Dimension(120, 35));
        btnKembali.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKembali);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pemasok"));

        jLabel2.setText("Kode");

        jLabel4.setText("Nama");

        jLabel5.setText("Telp");

        txtKodePemasok.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtKodePemasok.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                txtKodePemasokItemStateChanged(evt);
            }
        });

        txtNamaPemasok.setEditable(false);

        txtTelpPemasok.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKodePemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelpPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtKodePemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNamaPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTelpPemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtKode.setEditable(false);

        jLabel3.setText("Tanggal");

        jLabel1.setText("Kode");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Daftar Barang Belanjaan"));

        tableView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Jumlah"
            }
        ));
        jScrollPane1.setViewportView(tableView);
        if (tableView.getColumnModel().getColumnCount() > 0) {
            tableView.getColumnModel().getColumn(0).setMinWidth(120);
            tableView.getColumnModel().getColumn(0).setPreferredWidth(120);
            tableView.getColumnModel().getColumn(0).setMaxWidth(120);
            tableView.getColumnModel().getColumn(1).setMinWidth(200);
            tableView.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableView.getColumnModel().getColumn(2).setMinWidth(80);
            tableView.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableView.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        btnTambahBarang.setText("Tambah");
        btnTambahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBarangActionPerformed(evt);
            }
        });

        btnHapusBarang.setText("Hapus");
        btnHapusBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnTambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTambahBarang, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnHapusBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahBarangActionPerformed
        DataPemesananPembelianDetailView view = new DataPemesananPembelianDetailView(menuController, true, pesanBarang, this);
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }//GEN-LAST:event_btnTambahBarangActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        try {
            DataPemesananPembelianView view = new DataPemesananPembelianView(menuController);
            menuController.setInnerLayout(view);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(DataPemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try {
            this.pesanBarang.setTgl(Date.valueOf(ValueFormatterFactory.getDateSQL(txtTanggal.getDate())));
            this.pesanBarang.setPemasok(daftarPemasok.get(txtKodePemasok.getSelectedIndex()));

            repoPesananaPembelian.save(pesanBarang, daftarBelajaan);
            JOptionPane.showMessageDialog(this, "Daftar belanjaan " + pesanBarang.getKode() + " berhasil disimpan!", getTitle(), JOptionPane.INFORMATION_MESSAGE);

            DataPemesananPembelianView view = new DataPemesananPembelianView(menuController);
            menuController.setInnerLayout(view);
        } catch (SQLException ex) {
            Logger.getLogger(DataPemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(DataPemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void txtKodePemasokItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_txtKodePemasokItemStateChanged
        if (txtKodePemasok.getSelectedItem() != null) {
            Pemasok pemasok = daftarPemasok.get(txtKodePemasok.getSelectedIndex());
            setFieldPemasok(pemasok);
        } else {
            clearFieldPemasok();
        }
    }//GEN-LAST:event_txtKodePemasokItemStateChanged

    private void btnHapusBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusBarangActionPerformed
        if (tableController.isSelected()) {
            PesananPembelianDetail pd = daftarBelajaan.get(tableController.getRowSelected());
            boolean remove = daftarBelajaan.remove(pd);
            refreshDataTable();
        } else {
            JOptionPane.showMessageDialog(this, "Data Barang belanjaan belum pilih", getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusBarangActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        if (daftarBelajaan.size() > 0) {
            try {
                String url = "/laporan/DaftarBelanjaan.jasper";
                Map<String, Object> parametters = new HashMap<>();
                parametters.put("kode", this.pesanBarang.getKode());
                parametters.put("tanggal", txtTanggal.getDate());

                if (txtKodePemasok.getSelectedItem() != null) {
                    Pemasok p = daftarPemasok.get(txtKodePemasok.getSelectedIndex());
                    parametters.put("kodePemasok", p.getTlp());
                    parametters.put("namaPemasok", p.getNama());

                }
                JasperPrint print = JasperFillManager.fillReport(
                        getClass().getResourceAsStream(url),
                        parametters,
                        new JRBeanCollectionDataSource(daftarBelajaan));
                JasperViewer view = new JasperViewer(print);
                view.setLocationRelativeTo(null);
                view.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(DataPemesananPembelianView.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang akan dicetak", getTitle(), JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapusBarang;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tableView;
    private javax.swing.JTextField txtKode;
    private javax.swing.JComboBox<String> txtKodePemasok;
    private javax.swing.JTextField txtNamaPemasok;
    private com.toedter.calendar.JDateChooser txtTanggal;
    private javax.swing.JTextField txtTelpPemasok;
    // End of variables declaration//GEN-END:variables
}
