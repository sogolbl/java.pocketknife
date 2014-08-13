package rleano.util;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GitUtil {

	public static Set<String> getFiles(String gitFolder, String hash) {

		Set<String> changed = new LinkedHashSet<String>();
		String command = "git diff-tree --no-commit-id --name-only -r " + hash;
		String[] lines = Util.cmdExec(command.split(" "), gitFolder + "/").split("\\r?\\n");
		for (String line : lines) {
			if (line.trim().isEmpty() == false) {
				changed.add(line.trim());
			}
		}

		return changed;
	}

	/**
	 * Searches all commits for those 
	 * @param gitFolder	where the gitcommand should be executed
	 * @param hash	hash of the commit to find, use "HEAD" for all
	 * @param options	(e.g. "--min-parents=2"), if no options then "" or null can be used
	 * @return
	 */
	public static List<String> getCommitsWith(String gitFolder, String text) {
		String command = "git log --oneline --format=%h --grep=" + text;
		List<String> commits = Util.cmdExec(command, gitFolder);
		String[] lines = Util.cmdExec(command.split(" "), gitFolder + "/").split("\\r?\\n");
		for (String line : lines) {
			if (line.trim().isEmpty() == false) {
				commits.add(line.trim());
			}
		}

		return commits;
	}

	/**
	 * Gets the author's date of a given commit
	 * @param gitFolder	where the gitcommand should be executed
	 * @param hash
	 * @return
	 */
	public static String getCommitDate(String gitFolder, String hash) {
		String command = "git show -s --date=rfc --format=%ai " + hash;
		return Util.cmdExecLiner(command, gitFolder);
	}

	/**
	 * Gets the comment of a given commit
	 * @param gitFolder	where the gitcommand should be executed
	 * @param hash
	 * @return
	 */
	public static String getCommitComment(String gitFolder, String hash) {
		String command = "git log --format=%B -1 " + hash;
		return Util.cmdExecLiner(command, gitFolder);
	}

	/**
	 * Gets the data (except comment) of a given commit by lines.
	 * 	<li>hash</li>
	 * 	<li>parents (space separated)</li>
	 * 	<li>author</li>
	 * 	<li>author's email</li>
	 * 	<li>author's date</li>
	 * 	<li>committer</li>
	 * 	<li>committer's email</li>
	 * 	<li>committer's date</li>
	 * @param gitFolder	where the gitcommand should be executed
	 * @param hash
	 * @return
	 */
	public static List<String> getCommit(String gitFolder, String hash) {
		String command = "git log -1 --format=%H%n%P%n%an%n%ae%n%ai%n%cn%n%ce%n%ci " + hash;
		return Util.cmdExec(command, gitFolder);
	}

	/**
	 * Gets all commit hashes given an initial commit, branch and options
	 * @param gitFolder	where the gitcommand should be executed
	 * @param hash	hash of the commit to find, use "HEAD" for all
	 * @param options	(e.g. "--min-parents=2"), if no options then "" or null can be used
	 * @return
	 */
	public static List<String> getCommits(String gitFolder, String hash, String options) {
		String command = "git rev-list ";
		if (options != null && options.isEmpty() == false) {
			command += options.trim() + " ";
		}
		command += hash;
		List<String> result = Util.cmdExec(command, gitFolder);
		return result;
	}

	/**
	 * Gets total number of commits given an initial commit, branch and options
	 * @param gitFolder	where the gitcommand should be executed
	 * @param hash	hash of the commit to find, use "HEAD" for all
	 * @param options	(e.g. "--min-parents=2"), if no options then "" or null can be used
	 * @return
	 */
	public static int getTotalCommits(String gitFolder, String hash, String options) {
		String command = "git rev-list --count ";
		if (options != null && options.isEmpty() == false) {
			command += options.trim() + " ";
		}
		command += hash;
		String result = Util.cmdExecLiner(command, gitFolder);
		return Integer.parseInt(result.trim());
	}

	public static List<String> getCommitsByIssue(String gitFolder, String issue) {
		String command = "git log --oneline --format=%h --grep " + issue + "[^0-9] --grep=" + issue + "$";
		List<String> result = Util.cmdExec(command, gitFolder);
		return result;
	}
}
