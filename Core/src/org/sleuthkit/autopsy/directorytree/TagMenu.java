/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sleuthkit.autopsy.directorytree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.sleuthkit.autopsy.datamodel.Tags;
import org.sleuthkit.autopsy.datamodel.Tags.Taggable;
import org.sleuthkit.autopsy.datamodel.Tags.TaggableBlackboardArtifact;
import org.sleuthkit.autopsy.datamodel.Tags.TaggableFile;
import org.sleuthkit.datamodel.AbstractFile;
import org.sleuthkit.datamodel.BlackboardArtifact;

/**
 * The menu that results when one right-clicks on a file or artifact.
 */
public class TagMenu extends JMenu {
    
    private Taggable tagCreator;
    
    public TagMenu(AbstractFile file) {
        super("Tag File");
        tagCreator = new TaggableFile(file);
        init();
    }
    
    public TagMenu(BlackboardArtifact bba) {
        super("Tag Result");
        tagCreator = new TaggableBlackboardArtifact(bba);
        init();
    }

    private void init() {
        
        // create the 'Quick Tag' menu and add it to the 'Tag File' menu
        JMenu quickTagMenu = new JMenu("Quick Tag");
        add(quickTagMenu);
        
        // create the 'Quick Tag' sub-menu items and add them to the 'Quick Tag' menu
        List<String> tagNames = Tags.getTagNames();
        if (tagNames.isEmpty()) {
            JMenuItem empty = new JMenuItem("No tags");
            empty.setEnabled(false);
            quickTagMenu.add(empty);
        }
            
        for (final String tagName : tagNames) {
            JMenuItem tagItem = new JMenuItem(tagName);
            tagItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tagCreator.createTag(tagName, "");
                    refreshDirectoryTree();
                }
            });
            quickTagMenu.add(tagItem);
        }
        
        quickTagMenu.addSeparator();
            
        // create the 'New Tag' menu item
        JMenuItem newTagMenuItem = new JMenuItem("New Tag");
        newTagMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTagName = CreateTagDialog.getNewTagNameDialog(null);
                if (newTagName != null) {
                    tagCreator.createTag(newTagName, "");
                    refreshDirectoryTree();
                }
            }
        });

        // add the 'New Tag' menu item to the 'Quick Tag' menu
        quickTagMenu.add(newTagMenuItem);

        JMenuItem newTagItem = new JMenuItem("Tag and Comment");
        newTagItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new TagAndCommentDialog(tagCreator);
            }
        });
        add(newTagItem);
    }
    
    private void refreshDirectoryTree() {
        //TODO instead should send event to node children, which will call its refresh() / refreshKeys()
        DirectoryTreeTopComponent viewer = DirectoryTreeTopComponent.findInstance();
        viewer.refreshTree(BlackboardArtifact.ARTIFACT_TYPE.TSK_TAG_FILE);
        viewer.refreshTree(BlackboardArtifact.ARTIFACT_TYPE.TSK_TAG_ARTIFACT);
    }
}
