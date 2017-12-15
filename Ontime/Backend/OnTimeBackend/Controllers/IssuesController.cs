﻿using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using OnTimeBackend.Data;
using Uber4Cream.Data.DatabaseModels;
using Microsoft.AspNetCore.Identity;
using OnTimeBackend.Models;
using OnTimeBackend.Models.AccountViewModels;
using IdentityServer4.AccessTokenValidation;
using Microsoft.AspNetCore.Authorization;
using System;

namespace OnTimeBackend.Controllers
{
    [Authorize(AuthenticationSchemes = IdentityServerAuthenticationDefaults.AuthenticationScheme, Policy = "Access Resources")]
    [Produces("application/json")]
    [Route("api/Issues")]
    public class IssuesController : Controller
    {
        private readonly ApplicationDbContext context;
        private readonly UserManager<ApplicationUser> usermanager;

        public IssuesController(ApplicationDbContext context, UserManager<ApplicationUser> usermanager)
        {
            this.context = context;
            this.usermanager = usermanager;
        }

        // GET: api/Issues
        [HttpGet]
        public IEnumerable<Issue> Getissues()
        {
            return context.issues.Include(i => i.reason).Include(i=> i.location);
        }

        [HttpGet("issuesfromuser")]
        public async Task<IActionResult> getallissuesfromuser()
        {
            var user = await usermanager.GetUserAsync(User);

            var issues = await context.issues.Where(i => i.employee.IdentityID == user.Id).Include(i => i.location).Include(i => i.reason).ToListAsync();

            if(issues != null)
            {
                return new JsonResult(issues);
            }


            return BadRequest();
        }

        [HttpPost("changestatus")]
        public async Task<IActionResult> changestatususer(int id)
        {
            var user = await usermanager.GetUserAsync(User);

            var issue = await context.issues.Where(i => i.IssueID == id).FirstAsync();
            
            if (issue != null)
            {
                issue.IssueClosed = !issue.IssueClosed;
                await context.SaveChangesAsync();
                return Ok();
            }


            return BadRequest();
        }


        // POST: api/Issues
        [HttpPost]
        public async Task<IActionResult> PostIssue([FromBody] CreateIssue tempissue)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var tokenUser = await usermanager.GetUserAsync(User);

            var employee = await context.employees.Where(em => em.IdentityID == tokenUser.Id).FirstOrDefaultAsync();

            if(employee != null)
            {
                var issue = new Issue
                {
                    location = tempissue.location,
                    reason = tempissue.reason,
                    employee = employee,
                    IssueCreated = DateTime.Now
                };

                await context.issues.AddAsync(issue);
                await context.SaveChangesAsync();

                return Ok();

            }

            return BadRequest();

        }

        // DELETE: api/Issues/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteIssue([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var issue = await context.issues.SingleOrDefaultAsync(m => m.IssueID == id);
            if (issue == null)
            {
                return NotFound();
            }

            context.issues.Remove(issue);
            await context.SaveChangesAsync();

            return Ok(issue);
        }

        private bool IssueExists(int id)
        {
            return context.issues.Any(e => e.IssueID == id);
        }
    }
}