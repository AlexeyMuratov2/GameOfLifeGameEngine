package org.example.view.menu;

import org.example.db.CustomRuleRepository;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RuleManagementDialog extends JDialog {
    public RuleManagementDialog(JFrame parent, CustomRuleRepository repository, JComboBox<String> ruleBoxToUpdate) {
        super(parent, "Manage Rules", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        List<String> customRules = repository.findAllCustomRules();
        customRules.forEach(listModel::addElement);

        JList<String> ruleList = new JList<>(listModel);
        add(new JScrollPane(ruleList), BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete Selected Rule");
        deleteButton.addActionListener(e -> {
            String selected = ruleList.getSelectedValue();
            if (selected != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Delete rule: " + selected + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    repository.deleteCustomRule(selected);
                    listModel.removeElement(selected);
                    ruleBoxToUpdate.removeItem(selected);
                }
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
