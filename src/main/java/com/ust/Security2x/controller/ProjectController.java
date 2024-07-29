package com.ust.Security2x.controller;

import com.ust.Security2x.model.Project;
import com.ust.Security2x.model.ProjectStatus;
import com.ust.Security2x.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;


    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    public String addProject(@RequestBody Project project) {
        if (project.getProjectstatus() == null) {
            project.setProjectstatus(ProjectStatus.INPROGRESS);
        }
        projectRepository.save(project);
        return "Project added successfully";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PutMapping("/{projectId}/approve")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String approveProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        project.setProjectstatus(ProjectStatus.COMPLETED);
        projectRepository.save(project);
        return "Project approved successfully";
    }

    @PutMapping("/{projectId}/remove")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String removeProject(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));
        project.setProjectstatus(ProjectStatus.REJECTED);
        projectRepository.save(project);
        return "Project removed successfully";
    }
}
