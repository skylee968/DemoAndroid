package com.onetech.otcore.http.queue;

public abstract interface QueueCommand {
	public abstract void execute();

	public abstract void cancel();
}
