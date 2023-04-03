# team-template
Project team template

At some point, you'll need to replace this file to describe your project, how to get it running, and that sort of thing.

Meanwhile, here are some tips to get started.

## using the repository

1. Everyone on the team must clone this project repository to their computers
3. Remember to always create and check out a branch before changing files
2. Create issues here in GitHub! Lots of issues! Anything you do for the project could be an issue...
    * Create an issue because you need to decide what the software is going to be.
    * Create an issue because you need to learn how to use the programming language/framework.
    * Create an issue because you need to write the Software Requirements Specification
    * And, of course, create issues because you want to add features or fix bugs.
3. Every issue is a discussion. Participate!
4. Refer to the issues in which you've participated in your stand-up quiz so we know what you've been doing for the project even if it isn't code merged into master.
5. A Project lets you organize issues on a progress board to see what's on hold, being worked on, done. You can have multiple Project boards within a repository. Maybe create one for documentation, or artwork if your project needs that.

## Files to change in this template repo
Probably all of them!

**.gitignore**: I created this at https://gitignore.io/ with the settings macos,windows,linux,jetbrains,eclipse,visualstudiocode,java,c++,python. You might need more. You might want less.

**LICENSE**: This is the Creative Commons 0 (public domain) license which may not be compatible with third-party code you might include in your project. Probably isn't. So figure out what's appropriate and change it.

**README.md** That's this file. Once everyone is set up, replace it with a description of your project. Once your project runs, include instructions for installing and running it!

# using a framework
If you're using a framework or IDE that wants to help you get started by creating your project folder, that's okay, but it's going to be a little more difficult to get it set up as the initial state of your project.

It's easiest if the tool your using will do the setup in an existing folder, your cloned project repository. If it won't, you might want me (david_h_brown@uri.edu) to help you in a remote session.

One person should do the setup, starting with ``git checkout -b initial_setup`` in this repository, then do the setup, get it working, commit the changes, and push them. Create a pull request (PR) and request a review.

The reviewer should then do a ``git fetch`` to update their repo relative to the origin, then the review can  check it out (``git checkout initial_setup``). If it works for the reviewer, too, then the PR can be approved. If not, say what happened in the PR discussion and figure out how to fix it. Once it works for at least one other person, it probably makes sense to merge it into master.

If you can't use an existing folder, this might work: run the tool, initialize git in that project folder, create/checkout the initial_setup branch and commit all the changes to it. Set up the remote origin and fetch. Rebase the initial_setup branch onto the master. Then push, PR, review and merge as with the easier setup. I haven't done this recently which is why I offered to help.
