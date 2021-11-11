/**
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org/>
 */
package com.github.leanan.sidhelle.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;

/**
 * TODO:
 *
 */
public class Logger {

	/**
	 * TODO:
	 */
	protected final org.slf4j.Logger logger;
	
	/**
	 * Create a new wrapper for a {@link org.slf4j.Logger}.
	 * @param type - {@link Class} that owns the logger
	 */
	public Logger(Class<?> type) {
		logger = LoggerFactory.getLogger(type);
	}
	
	/**
	 * TODO:
	 * @param format - {@link String} containing the message format
	 * @param arguments - {@link Object}s to mix into format
	 */
	public void info(String format,Object...arguments) {
		logger.info(format,arguments);
	}
	
	/**
	 * TODO:
	 * @param format - {@link String} containing the message format
	 * @param arguments - {@link Object}s to mix into format
	 */
	public void debug(String format,Object...arguments) {
		logger.debug(format,arguments);
	}
	
	/**
	 * TODO:
	 * @param format - {@link String} containing the message format
	 * @param arguments - {@link Object}s to mix into format
	 */
	public void warn(String format,Object...arguments) {
		logger.warn(format,arguments);
	}
	
	/**
	 * TODO:
	 * @param format - {@link String} containing the message format
	 * @param arguments - {@link Object}s to mix into format
	 */
	public void error(String format,Object...arguments) {
		logger.error(format,arguments);
	}
	
	/**
	 * TODO:
	 * @param format - {@link String} containing the message format
	 * @param arguments - {@link Object}s to mix into format
	 */
	public void trace(String format,Object...arguments) {
		logger.trace(format,arguments);
	}
	
	/**
	 * TODO:
	 * @param thrown - {@link Throwable} to log the stack trace for
	 */
	public void trace(Throwable thrown) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		thrown.printStackTrace(pw);
		logger.trace(sw.toString());
	}
	
	/**
	 * TODO:
	 */
	public void enter() {
		logger.debug("Entering {}.",callsite(2));
	}
	
	/**
	 * TODO:
	 */
	public void exit() {
		logger.debug("Exiting {}.",callsite(2));
	}
	
	/**
	 * TODO:
	 * @param depth
	 * @return
	 */
	protected String callsite(int depth) {
		Thread thread = Thread.currentThread();
		if(thread!=null) {
			StackTraceElement[] stack = thread.getStackTrace();
			if(stack!=null && stack.length>=depth+1) {
				StackTraceElement element = stack[depth + 1];
				return String.format("%s#%s (line %d in %s)",
					element.getClassName(),
					element.getMethodName(),
					element.getLineNumber(),
					element.getFileName());
			}
		}
		return null;
	}
}
