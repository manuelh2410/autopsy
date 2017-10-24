/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2017 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.communications;

import java.util.List;
import java.util.stream.Collectors;
import org.openide.explorer.ExplorerManager;
import org.openide.util.NbBundle;
import org.openide.windows.Mode;
import org.openide.windows.RetainLocation;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.coreutils.ThreadConfined;

/**
 * Top component which displays the Communications Visualization Tool.
 */
@TopComponent.Description(
        preferredID = "CVTTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", //use this to put icon in window title area,
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "cvt", openAtStartup = false)
@RetainLocation("cvt")
@NbBundle.Messages("CVTTopComponent.name= Communications Visualization")
public final class CVTTopComponent extends TopComponent implements ExplorerManager.Provider {

    private static final long serialVersionUID = 1L;

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    private final ExplorerManager em = new ExplorerManager();

    public CVTTopComponent() {
        initComponents();
        setName(Bundle.CVTTopComponent_name());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        HSplitPane = new javax.swing.JSplitPane();
        BrowseVisualizeTabPane = new javax.swing.JTabbedPane();
        accountsBrowser = new org.sleuthkit.autopsy.communications.AccountsBrowser();
        jPanel1 = new javax.swing.JPanel();
        VSplitPane = new javax.swing.JSplitPane();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        filtersPane = new org.sleuthkit.autopsy.communications.FiltersPanel();

        HSplitPane.setDividerLocation(600);

        BrowseVisualizeTabPane.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BrowseVisualizeTabPane.addTab(org.openide.util.NbBundle.getMessage(CVTTopComponent.class, "CVTTopComponent.accountsBrowser.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/communications/images/table.png")), accountsBrowser); // NOI18N

        jPanel1.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );

        BrowseVisualizeTabPane.addTab(org.openide.util.NbBundle.getMessage(CVTTopComponent.class, "CVTTopComponent.TabConstraints.tabTitle"), new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/communications/images/emblem-web.png")), jPanel1); // NOI18N

        HSplitPane.setLeftComponent(BrowseVisualizeTabPane);

        VSplitPane.setDividerLocation(200);
        VSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jTextField1.setText(org.openide.util.NbBundle.getMessage(CVTTopComponent.class, "CVTTopComponent.jTextField1.text")); // NOI18N
        VSplitPane.setTopComponent(jTextField1);

        jTextField2.setText(org.openide.util.NbBundle.getMessage(CVTTopComponent.class, "CVTTopComponent.jTextField2.text")); // NOI18N
        VSplitPane.setRightComponent(jTextField2);

        HSplitPane.setRightComponent(VSplitPane);

        filtersPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filtersPane, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(HSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1290, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filtersPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HSplitPane))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane BrowseVisualizeTabPane;
    private javax.swing.JSplitPane HSplitPane;
    private javax.swing.JSplitPane VSplitPane;
    private org.sleuthkit.autopsy.communications.AccountsBrowser accountsBrowser;
    private org.sleuthkit.autopsy.communications.FiltersPanel filtersPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        super.componentOpened();
        WindowManager.getDefault().setTopComponentFloating(this, true);
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return em;
    }

    @Override
    public List<Mode> availableModes(List<Mode> modes) {
        /*
         * This looks like the right thing to do, but online discussions seems
         * to indicate this method is effectively deprecated. A break point
         * placed here was never hit.
         */
        return modes.stream().filter(mode -> mode.getName().equals("cvt"))
                .collect(Collectors.toList());
    }
}
